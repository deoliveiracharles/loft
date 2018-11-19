package br.ufrpe.loftapp2.credentials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import br.ufrpe.loftapp2.MainActivity
import br.ufrpe.loftapp2.R

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun click(v : View) {
        val intent = Intent(this, CreateAccount::class.java )
        startActivity(intent)
    }

    fun bttnClick(v: View){
        val intent = Intent(this, MainActivity::class.java )
        startActivity(intent)
    }
}
