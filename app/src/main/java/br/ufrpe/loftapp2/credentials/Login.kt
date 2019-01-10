package br.ufrpe.loftapp2.credentials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.ufrpe.loftapp2.QRCodeReader
import br.ufrpe.loftapp2.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.visibility = View.VISIBLE
        login_progress.visibility = View.INVISIBLE

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        if(savedInstanceState != null){
            var email = savedInstanceState.getString("emailField")
            var password = savedInstanceState.getString("passwordField")
            emailField.setText(email)
            passwordField.setText(password)
        }
    }

    override fun onResume() {
        signInButton.visibility = View.VISIBLE
        login_progress.visibility = View.INVISIBLE
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    fun click(v : View) {
        val intent = Intent(this, CreateAccount::class.java )
        startActivity(intent)
    }

    fun bttnClick(v: View){

        var email: String? = emailField.text.toString()
        var password: String? = passwordField.text.toString()

        var message:String = getString(R.string.infoMissing)

        if (email!!.isEmpty() || password!!.isEmpty()){
            showMessage(message)
        }
        else{
            login(emailField.text.toString(), passwordField.text.toString())
        }
    }

    private fun login(email: String, password: String) {
        signInButton.visibility = View.INVISIBLE
        login_progress.visibility = View.VISIBLE
        var failMessage: String = getString(R.string.invalidUser)

        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    //val intent = Intent(this, MainActivity::class.java)
                    val intent = Intent(this, QRCodeReader::class.java)
                    startActivity(intent)
                }else{
                    signInButton.visibility = View.VISIBLE
                    login_progress.visibility = View.INVISIBLE
                    showMessage(failMessage)
                }
            }

    }

    private fun showMessage(string: String) {
        Toast.makeText(applicationContext, string, Toast.LENGTH_LONG).show()
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if(outState!= null){
            outState.putCharSequence("emailField", emailField.text)
            outState.putCharSequence("passwordField", passwordField.text)
        }
    }
}
