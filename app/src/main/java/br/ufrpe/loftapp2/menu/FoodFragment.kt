package br.ufrpe.loftapp2.menu

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.ufrpe.loftapp2.R
import br.ufrpe.loftapp2.adapter.ItemAdapter
import br.ufrpe.loftapp2.model.Item
import kotlinx.android.synthetic.main.food_layout.view.*

class FoodFragment: Fragment(){

    val itemList:ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadFoodItem()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = LayoutInflater.from(container?.context).inflate(R.layout.food_layout, container, false)
        v.rvItemList.addItemDecoration(SpaceItemDecoration(30, 50, 30))
        v.rvItemList.layoutManager = GridLayoutManager(context, 2)
        v.rvItemList.adapter = ItemAdapter(itemList, requireContext(), 1)
        return v
    }

    private fun loadFoodItem(){
        itemList.add(Item("Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))

        itemList.add(Item("Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))

        itemList.add(Item("Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Pastel de forno ", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))

        itemList.add(Item("Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))

        itemList.add(Item("Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        itemList.add(Item("Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))

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