package br.ufrpe.loftapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import br.ufrpe.loftapp.card.CardItemAdapter
import kotlinx.android.synthetic.main.activity_card.*

class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        toolbar.setNavigationIcon(R.drawable.menu_white)
        setSupportActionBar(toolbar)

        rvCardList.layoutManager = LinearLayoutManager(this)
        rvCardList.adapter = CardItemAdapter(Constants.card, this, 2)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
