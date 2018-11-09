package br.ufrpe.loftapp

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_comidas.*
import kotlinx.android.synthetic.main.fragment_comidas.view.*

class ComidasFragment: Fragment() {

    val itemList:ArrayList<Item> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadFoodItem()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater.inflate(R.layout.fragment_comidas, container, false)
        //v.rvItemList.layoutManager = LinearLayoutManager(context)
        v.rvItemList.addItemDecoration(SpaceItemDecoration(30, 50, 30))
        v.rvItemList.layoutManager = GridLayoutManager(context, 2)
        v.rvItemList.adapter = ItemAdapter(itemList, requireContext())

        return v
    }



    private fun loadFoodItem(){
        itemList.add(Item("Pão de queijo", 6, 5.0))
        itemList.add(Item("Coxinha", 10, 12.0))
        itemList.add(Item("Brigadeiro", 10, 8.0))
        itemList.add(Item("Pastel de forno", 6, 10.0))

        itemList.add(Item("Pão de queijo", 6, 5.0))
        itemList.add(Item("Coxinha", 10, 12.0))
        itemList.add(Item("Brigadeiro", 10, 8.0))
        itemList.add(Item("Pastel de ", 6, 10.0))

        itemList.add(Item("Pão de queijo", 6, 5.0))
        itemList.add(Item("Coxinha", 10, 12.0))
        itemList.add(Item("Brigadeiro", 10, 8.0))
        itemList.add(Item("Pastel de forno ", 6, 10.0))

        itemList.add(Item("Pão de queijo", 6, 5.0))
        itemList.add(Item("Coxinha", 10, 12.0))
        itemList.add(Item("Brigadeiro", 10, 8.0))
        itemList.add(Item("Pastel de forno", 6, 10.0))

        itemList.add(Item("Pão de queijo", 6, 5.0))
        itemList.add(Item("Coxinha", 10, 12.0))
        itemList.add(Item("Brigadeiro", 10, 8.0))
        itemList.add(Item("Pastel de forno", 6, 10.0))

    }
}

class SpaceItemDecoration(val spaceLeft: Int, val spaceTop: Int, val spaceRight: Int ): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = spaceTop
        outRect.left = spaceLeft
        outRect.right = spaceRight
    }
}