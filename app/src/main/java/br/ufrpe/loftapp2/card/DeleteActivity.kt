package br.ufrpe.loftapp2.card

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import br.ufrpe.loftapp2.R
import br.ufrpe.loftapp2.model.Item
import kotlinx.android.synthetic.main.activity_delete.*

class DeleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels
        val orientation = resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            window.setLayout((width*0.4).toInt(),(height*0.4).toInt())
        }else{
            window.setLayout((width*0.7).toInt(),(height*0.2).toInt())
        }


        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = 20
        window.attributes = params

        val item = intent.getSerializableExtra("item") as Item
        val position = intent.getIntExtra("position", -1)
        tvNameDelete.text = item.name
        btnCancelNo.setOnClickListener{
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }

        btnCancelYes.setOnClickListener{
            val intent = Intent()
            intent.putExtra("item", item)
            intent.putExtra("position", position)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }
    }
}
