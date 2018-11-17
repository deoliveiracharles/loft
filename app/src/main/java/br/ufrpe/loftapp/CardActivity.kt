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
        rvCardList.addItemDecoration(SpaceItemDecoration(0, 40, 0))
        rvCardList.layoutManager = LinearLayoutManager(this)
        rvCardList.adapter = CardItemAdapter(Constants.card, this, 2)

        updatePrice()

    }

    fun updatePrice(){
        var cardPrice = 0.0
        for(item in Constants.card){
            cardPrice += item.price
        }
        cardValue.text = cardPrice.toString()
        totalValue.text = cardPrice.toString()
        itemCount.text = Constants.card.size.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
