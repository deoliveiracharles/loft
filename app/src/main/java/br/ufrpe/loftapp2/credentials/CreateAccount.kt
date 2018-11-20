package br.ufrpe.loftapp2.credentials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.ufrpe.loftapp2.MainActivity
import br.ufrpe.loftapp2.R

class CreateAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
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
}
