package com.example.cinequiz.profile


import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cinequiz.R

class HelpProfile : AppCompatActivity() {

    val send by lazy { findViewById<Button>(R.id.bt_send) }
    val insertEmail by lazy { findViewById<EditText>(R.id.et_write_email) }
    val insertMessage by lazy { findViewById<EditText>(R.id.et_write_message)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_profile)


        insertEmail.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(insertEmail.text.toString()).matches())
                    send.isEnabled = true
                else{
                    send.isEnabled = false
                    insertEmail.setError("Insira um email válido")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


        send.setOnClickListener {
            if (insertMessage.text.isBlank()) {
                Toast.makeText(this, "Digite a mensagem", Toast.LENGTH_LONG).show()
            } else messageSent()

        }


    }

    fun messageSent() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Mensagem enviada com sucesso!")
//        builder.setPositiveButton("", { dialogInterface: DialogInterface, i: Int ->
//            finish()
//        })
        builder.setNegativeButton("OK", { dialogInterface: DialogInterface, i: Int -> })
        super.onBackPressed()
    }
}