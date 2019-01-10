package br.ufrpe.loftapp2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qrcode_reader.*
import android.R.attr.data
import com.google.zxing.integration.android.IntentResult
import java.lang.Double.parseDouble
import java.lang.NumberFormatException
import kotlin.math.roundToInt


class QRCodeReader : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_reader)

        btnDetect.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }
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
