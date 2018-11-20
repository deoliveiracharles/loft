package br.ufrpe.loftapp2.credentials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.ufrpe.loftapp2.MainActivity
import br.ufrpe.loftapp2.R
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        if(savedInstanceState != null){
            var email = savedInstanceState.getString("email")
            var password = savedInstanceState.getString("password")
            var name = savedInstanceState.getString("name")
            var phone = savedInstanceState.getString("phone")

            insert_email_edit.setText(email)
            insert_name_edit.setText(name)
            insert_password_edit.setText(password)
            insert_phone_edit.setText(phone)
        }
    }

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    fun bttnClick(v: View){
        val intent = Intent(this, Login::class.java )
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if(outState != null){
            outState.putCharSequence("email", insert_email_edit.text)
            outState.putCharSequence("name", insert_name_edit.text)
            outState.putCharSequence("phone", insert_phone_edit.text)
            outState.putCharSequence("password", insert_password_edit.text)
        }
    }
}
