package br.ufrpe.loftapp2.card

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.ufrpe.loftapp2.Constants
import br.ufrpe.loftapp2.R
import br.ufrpe.loftapp2.adapter.CardItemAdapter
import br.ufrpe.loftapp2.model.Item
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*

class CardFragment: Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = LayoutInflater.from(container?.context).inflate(R.layout.card_layout, container, false)
        v.rvCardList.addItemDecoration(SpaceItemDecoration(0, 40, 0))
        v.rvCardList.layoutManager = LinearLayoutManager(context)
        v.rvCardList.adapter = CardItemAdapter(Constants.card, requireContext(), 2, this)
        updatePrice(v)
        return v
    }

    fun updatePrice(v: View) {
        var cardPrice = 0.0
        for(item in Constants.card){
            cardPrice += item.price
        }
        v.cardValue.text = cardPrice.toString()
        v.totalValue.text = cardPrice.toString()
        v.itemCount.text = Constants.card.size.toString()
    }
    fun updatePrice() {
        var cardPrice = 0.0
        for(item in Constants.card){
            cardPrice += item.price
        }
        cardValue.text = cardPrice.toString()
        totalValue.text = cardPrice.toString()
        itemCount.text = Constants.card.size.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.RequestCodeDelete && resultCode == Activity.RESULT_OK){
            val item = data?.getSerializableExtra("item") as Item
            val position = data?.getIntExtra("position", -1)
            Constants.card.removeAt(position)
            rvCardList.adapter!!.notifyItemRemoved(position)
            rvCardList.adapter!!.notifyItemRangeChanged(position, Constants.card.size)
            Toast.makeText(context,"Item Deletado" , Toast.LENGTH_LONG).show()
            updatePrice()
        }

    }

    inner class SpaceItemDecoration(val spaceLeft: Int, val spaceTop: Int, val spaceRight: Int ): RecyclerView.ItemDecoration(){
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = spaceTop
            outRect.left = spaceLeft
            outRect.right = spaceRight
        }
    }
}