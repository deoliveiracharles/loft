package br.ufrpe.loftapp

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        window.setLayout((width*0.8).toInt(),(height*0.8).toInt())

        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = 20
        window.attributes = params

        val item = intent.getSerializableExtra("item") as Item

        tvNameDetail.text = item.name
        tvPriceDetail.text = item.price.toString()
        tvUnitsDetail.text = item.units.toString()
        tvIngredients.text = item.ingredients

    }
}
