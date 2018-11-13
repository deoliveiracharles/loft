package br.ufrpe.loftapp

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels

        window.setLayout((width*0.8).toInt(),(height*0.6).toInt())

        var params = window.attributes
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = 20
        window.attributes = params

        var item = intent.getSerializableExtra("item") as Item

        tvName.text = item.name.toString()
        tvPrice.text = item.price.toString()
    }
}
