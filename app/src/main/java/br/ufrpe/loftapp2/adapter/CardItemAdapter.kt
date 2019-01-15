package br.ufrpe.loftapp2.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.ufrpe.loftapp2.Constants
import br.ufrpe.loftapp2.R
import br.ufrpe.loftapp2.card.DeleteActivity
import br.ufrpe.loftapp2.menu.DetailActivity
import br.ufrpe.loftapp2.model.Item
import kotlinx.android.synthetic.main.card_item_view.view.*
import kotlinx.android.synthetic.main.item_view.view.*


class CardItemAdapter(private val items: ArrayList<Item>, private val context: Context, private val typeAdapter:Int, private val fragment:Fragment) : RecyclerView.Adapter<ViewHolderCard>(){

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderCard {
        return ViewHolderCard(LayoutInflater.from(context).inflate(R.layout.card_item_view, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolderCard, position: Int) {

        p0.tvName.text = items[position].name
        p0.tvPrice.text = items[position].price.toString()
        p0.ivDelete.setOnClickListener{
            val intent = Intent(context, DeleteActivity::class.java)
            intent.putExtra("item", items[position])
            intent.putExtra("position", position)
            //val contexto = context as Activity
            //contexto.startActivityForResult(intent, Constants.RequestCodeDelete)
            fragment.startActivityForResult(intent, Constants.RequestCodeDelete)
            //items.remove(items[position])
            //notifyItemRemoved(position)
            //notifyItemRangeChanged(position, items.size)

        }
        if (items[position].isConfirmed){
            p0.ivDelete.visibility = View.INVISIBLE
        }else{
            p0.ivDelete.visibility = View.VISIBLE
        }
        p0.itemView.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("item", items[position])
            intent.putExtra("typeAdapter", typeAdapter)
            val contexto = context as Activity
            contexto.startActivityForResult(intent, Constants.RequestCodeComidas)
        }
    }
    fun getItems(): ArrayList<Item> {
        return items
    }

}

class ViewHolderCard (view: View) : RecyclerView.ViewHolder(view){
    val tvName = view.tvCardName!!
    val ivDelete = view.ivCardDelete!!
    val tvPrice = view.tvCardPrice!!

}
