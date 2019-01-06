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

class CocktailFragment: Fragment(){

    val itemList:ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadCocktailItem()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = LayoutInflater.from(container?.context).inflate(R.layout.cocktail_layout, container, false)
        v.rvItemList.addItemDecoration(SpaceItemDecoration(30, 50, 30))
        v.rvItemList.layoutManager = GridLayoutManager(context, 2)
        v.rvItemList.adapter = ItemAdapter(itemList, requireContext(), 1)
        return v
    }

    private fun loadCocktailItem(){
        itemList.add(Item("1","Sex on The Beach", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("2","Margarita", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("3","White Russian", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("4","Lemon Drop", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("5","Gimlet", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("6","Negroni", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("7","Manhattan", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("8","Mimosa", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("9","Sazerac", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("10","Sidecar", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("11","Martini", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("12","Daiquiri", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("13","Tuthilltown Toddy", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("14","Cinnamon Tequila Hot Toddy", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("15","Prince Pum King", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("16","Docteur du Miel", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("17","Mulled Wine", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("18","Apple Cardamom Toddy", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("19","Hot Honey Butter", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("20","Ginger Apple Cooler", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
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