package br.ufrpe.loftapp2.credentials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.ufrpe.loftapp2.MainActivity
import br.ufrpe.loftapp2.R
import kotlinx.android.synthetic.main.activity_create_account.*
import com.google.firebase.auth.FirebaseAuth

class CreateAccount : AppCompatActivity() {

    // [START declare_auth]
    private lateinit var mAuth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        create_account_button.visibility = View.VISIBLE
        createAccountProgress.visibility = View.INVISIBLE

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

        var email: String? = insert_email_edit.text.toString()
        var name: String? = insert_name_edit.text.toString()
        var phone: String? = insert_phone_edit.text.toString()
        var password: String? = insert_password_edit.text.toString()

        var invalidInfo: String = getString(R.string.invalidInformation)
        var invalidEmail: String = getString(R.string.invalidEmail)

        if (email!!.isEmpty() || name!!.isEmpty() || phone!!.isEmpty() || password!!.isEmpty() || !isValidEmail(email)){
            if (!isValidEmail(email)){
                showMessage(invalidEmail)
            }
            else{
                showMessage(invalidInfo)
            }
        }
        else{
            createAccount (name, phone, email, password)
        }
    }

    private fun createAccount(name: String, phone: String, email: String, password: String) {

        var accountCreated: String = getString(R.string.accountCreatedSuccessfuly)
        create_account_button.visibility = View.INVISIBLE
        createAccountProgress.visibility = View.VISIBLE

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful) {
                    showMessage(accountCreated)
                    val intent = Intent(this, Login::class.java )
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    create_account_button.visibility = View.VISIBLE
                    createAccountProgress.visibility = View.INVISIBLE
                    showMessage(task.exception!!.message.toString())
                }
            }

    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun showMessage(string: String){
        Toast.makeText(applicationContext, string, Toast.LENGTH_LONG).show()
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
