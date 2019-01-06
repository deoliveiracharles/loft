package br.ufrpe.loftapp2.credentials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.ufrpe.loftapp2.credentials.User
import br.ufrpe.loftapp2.R
import kotlinx.android.synthetic.main.activity_create_account.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CreateAccount : AppCompatActivity() {

    // [START declare_auth]
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user: User
    private var firebaseDatabase: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        progress(false)

        if(savedInstanceState != null){
            var name = savedInstanceState.getString("name")
            var phone = savedInstanceState.getString("phone")
            var email = savedInstanceState.getString("email")
            var password = savedInstanceState.getString("password")

            insert_name_edit.setText(name)
            insert_phone_edit.setText(phone)
            insert_email_edit.setText(email)
            insert_password_edit.setText(password)
        }
    }

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    fun bttnClick(v: View){

        var name: String? = insert_name_edit.text.toString()
        var phone: String? = insert_phone_edit.text.toString()
        var email: String? = insert_email_edit.text.toString()
        var password: String? = insert_password_edit.text.toString()

        var invalidInfo: String = getString(R.string.invalidInformation)
        var invalidEmail: String = getString(R.string.invalidEmail)

        if (name!!.isEmpty() || phone!!.isEmpty() || email!!.isEmpty() ||  password!!.isEmpty() || !isValidEmail(email)){
            if (!isValidEmail(email!!)){
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

        progress(true)

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful) {
                    storeUserOnDatabase(name, phone, email, password)

                    val intent = Intent(this, Login::class.java )
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    progress(false)
                    showMessage(task.exception!!.message.toString())
                }
            }

    }

    private fun storeUserOnDatabase(name: String, phone: String, email: String,  password: String){
        val ref = firebaseDatabase!!.getReference("users")
        val userId:String = ref.push().key!!
        val user = User(userId, name, phone, email, password)

        var accountCreated: String = getString(R.string.accountCreatedSuccessfuly)

        ref.child(userId).setValue(user)
        showMessage(accountCreated)
    }

    fun progress (progress: Boolean){
        if (progress){
            create_account_button.visibility= View.INVISIBLE
            createAccountProgress.visibility = View.VISIBLE
        }
        else{
            create_account_button.visibility= View.VISIBLE
            createAccountProgress.visibility = View.INVISIBLE
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
