package br.ufrpe.loftapp2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qrcode_reader.*
import android.R.attr.data
import br.ufrpe.loftapp2.model.Item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentResult
import java.lang.Double.parseDouble
import java.lang.NumberFormatException
import java.util.HashMap
import kotlin.math.roundToInt


class QRCodeReader : AppCompatActivity() {

    var firebaseDatabase: FirebaseDatabase? = null
    val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_reader)

        firebaseDatabase = FirebaseDatabase.getInstance()
        loadPreExistingCardFirebase() // ----------------------
        btnDetect.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }

        //this.loadPreExistingCardFirebase()
    }

    private fun loadPreExistingCardFirebase(){
        val cardRef = firebaseDatabase!!.getReference("cards")
        cardRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(Constants.controlador == 0){
                    Constants.confirmedCard.clear()
                    for(snapshot in p0.children){
                        val hashMap = snapshot.value as HashMap<String, String>
                        if(hashMap.size > 0 && Constants.controlador == 0){
                            if(hashMap["userEmail"].toString() == user!!.email){
                                var hashItem = hashMap["item"] as HashMap<String, String>
                                var item = Item(hashItem["id"].toString(), hashItem["name"].toString(), hashItem["units"].toString().toInt(), hashItem["price"].toString().toDouble(), hashItem["igredients"].toString(), hashItem["imageLink"].toString())
                                item.isConfirmed = true
                                Constants.confirmedCard.add(item)
                            }
                        }
                    }
                    Constants.controlador = 1
                }




            }
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(applicationContext, "Problem reading from server", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if(result != null){
                if(result.contents == null){

                }else{
                    var isNumber = true
                    var tableNumber = -1
                    try{
                        tableNumber = parseDouble(result.contents).roundToInt()
                    }catch (e:NumberFormatException){
                        isNumber = false
                    }

                    if(isNumber){
                        Toast.makeText(this, getString(R.string.Table) + ": "+  result.contents, Toast.LENGTH_LONG).show()

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("tableNumber", tableNumber)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, getString(R.string.problemQr), Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this, getString(R.string.problemQr), Toast.LENGTH_LONG).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}
