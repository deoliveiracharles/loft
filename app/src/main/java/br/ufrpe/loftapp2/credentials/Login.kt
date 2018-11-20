package br.ufrpe.loftapp2.credentials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.ufrpe.loftapp2.MainActivity
import br.ufrpe.loftapp2.R
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(savedInstanceState != null){
            var email = savedInstanceState.getString("emailField")
            var password = savedInstanceState.getString("passwordField")
        }
    }

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    fun click(v : View) {
        val intent = Intent(this, CreateAccount::class.java )
        startActivity(intent)
    }

    fun bttnClick(v: View){
        val intent = Intent(this, MainActivity::class.java )
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if(outState!= null){
            outState.putCharSequence("emailField", emailField.text)
            outState.putCharSequence("passwordField", passwordField.text)
        }
    }
}
