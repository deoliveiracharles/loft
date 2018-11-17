package br.ufrpe.loftapp.card

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.ufrpe.loftapp.Constants
import br.ufrpe.loftapp.DetailActivity
import br.ufrpe.loftapp.Item
import br.ufrpe.loftapp.R
import kotlinx.android.synthetic.main.card_item_view.view.*


class CardItemAdapter(private val items: ArrayList<Item>, private val context: Context, private val typeAdapter:Int) : RecyclerView.Adapter<ViewHolder>(){

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item_view, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {

        p0?.tvName?.text = items[position].name
        p0?.tvPrice?.text = items[position].price.toString()
        p0?.ivDelete?.setOnClickListener{
            val intent = Intent(context, DeleteActivity::class.java)
            intent.putExtra("item", items[position])
            intent.putExtra("position", position)
            val contexto = context as Activity
            contexto.startActivityForResult(intent, Constants.RequestCodeDelete)
            //items.remove(items[position])
            //notifyItemRemoved(position)
            //notifyItemRangeChanged(position, items.size)

        }
        p0?.itemView.setOnClickListener{
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

class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
    val tvName = view.tvCardName!!
    val ivDelete = view.ivCardDelete!!
    val tvPrice = view.tvCardPrice!!

}
