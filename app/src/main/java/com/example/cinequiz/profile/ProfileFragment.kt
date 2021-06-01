package com.example.cinequiz.profile

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cinequiz.EsqueciASenhaActivity
import com.example.cinequiz.LoginActivity
import com.example.cinequiz.R
import com.example.cinequiz.profile.imageCapture.FileHelper
import com.example.cinequiz.profile.imageCapture.PermissionsHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import java.io.File


class ProfileFragment : Fragment() {
    private val linkAboutApp by lazy { view?.findViewById<TextView>(R.id.textview_about) }
    private val linkHelpProfile by lazy { view?.findViewById<TextView>(R.id.textview_help) }
    private val changePass by lazy { view?.findViewById<TextView>(R.id.changePass) }
    private val deleteAccount by lazy { view?.findViewById<TextView>(R.id.deleteAccount) }
    private val logoff by lazy { view?.findViewById<TextView>(R.id.logoff) }
    private val picture by lazy { view?.findViewById<ImageView>(R.id.profileImageProfile) }
    private val displayName by lazy { view?.findViewById<TextView>(R.id.displayName) }
    private val photoBtn by lazy { view?.findViewById<FloatingActionButton>(R.id.photoButton) }

    lateinit var viewModel: ProfileViewModel
    lateinit var googleSignIn: GoogleSignInClient

    var fileShare: File? = null
    private var imageUri : Uri? = null
    private lateinit var permissionsHelper:PermissionsHelper

    companion object {
        const val FILE_AUTHORITY = "com.example.cinequiz"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionsHelper = PermissionsHelper(view.context as Activity)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestProfile()
            .requestEmail()
            .build()

        googleSignIn = GoogleSignIn.getClient(view.context, googleSignInOptions)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        linkAboutApp?.setOnClickListener {
            val intent = Intent(it.context, AboutApp::class.java)
            startActivity(intent)
        }
        linkHelpProfile?.setOnClickListener {
            val intent = Intent(it.context, HelpProfile::class.java)
            startActivity(intent)
        }
        changePass?.setOnClickListener {
            val intent = Intent(it.context, EsqueciASenhaActivity::class.java)
            startActivity(intent)
        }
        logoff?.setOnClickListener {
            val intent = Intent(it.context, LoginActivity::class.java)
            val callback = requireActivity() as CallbackToControlLogin
            viewModel.logoff(googleSignIn)
            callback.logoutClick()
            startActivity(intent)
        }
        deleteAccount?.setOnClickListener {
            AlertDialog.Builder(view.context)
                .setTitle("Confirmação")
                .setMessage("Você tem certeza que quer deletar a sua conta? Ao deletar todas as suas informações serão discartadas, sendo impossivel recuperar os dados")
                .setPositiveButton("Confirmar"){ _, _->
                    viewModel.deleteAccount(googleSignIn)
                    val intent = Intent(it.context, LoginActivity::class.java)
                    val callback = requireActivity() as CallbackToControlLogin
                    callback.logoutClick()
                    startActivity(intent)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
        }

        viewModel.name.observe(viewLifecycleOwner){
            displayName?.text = it
        }

        viewModel.photo.observe(viewLifecycleOwner){
            if (it!=null)
                Picasso.get().load(it).into(picture)
        }

        photoBtn?.setOnClickListener {
            takePhotoClick()
        }
    }

    private fun takePhotoClick(){
        val permissions = listOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionsHelper.requestAllPermission(permissions)) {
            openChooser()
        }
    }

    private fun openChooser() {
        val intentList = mutableListOf<Intent>()

        //takePhotoIntent
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file = FileHelper.createFileInStorage(view?.context!!)
        val uri = file?.let { FileProvider.getUriForFile(view?.context!!, FILE_AUTHORITY, it) }

        fileShare = file

        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

        //pickImageIntent
        val pickIntent = Intent()
        pickIntent.type = "image/*"
        pickIntent.action = Intent.ACTION_GET_CONTENT

        //Adiciona na lista de intents
        intentList.add(pickIntent)
        intentList.add(takePhotoIntent)

        val chooserIntent = Intent.createChooser(intentList[0], "Escolha como tirar a fotografia:")
        chooserIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS,
            intentList.toTypedArray()
        )

        startActivityForResult(chooserIntent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (isIntentFromCamera(requestCode, resultCode, intent)) {
            val image = BitmapFactory.decodeFile(fileShare?.path)
            picture?.background = null
            picture?.setImageBitmap(image)

            imageUri = parentFragment?.context?.let {
                FileProvider.getUriForFile(
                    it,
                    FILE_AUTHORITY,
                    fileShare!!
                )
            }
            viewModel.changeProfileImage(imageUri)
        } else if (isIntentFromFiles(requestCode, resultCode, intent)) {
            val pic = intent?.data as Uri
            imageUri = pic
            picture?.background = null
            picture?.setImageURI(pic)
            viewModel.changeProfileImage(pic)
        }
    }

    private fun isIntentFromFiles(
        requestCode: Int,
        resultCode: Int,
        intent: Intent?
    ) = requestCode == 200 && resultCode == Activity.RESULT_OK && intent?.data != null

    private fun isIntentFromCamera(
        requestCode: Int,
        resultCode: Int,
        intent: Intent?
    ) =
        requestCode == 200 && resultCode == Activity.RESULT_OK && (intent == null || intent.extras == null) && intent?.data == null
}




