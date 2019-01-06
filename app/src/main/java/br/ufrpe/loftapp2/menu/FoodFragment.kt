package br.ufrpe.loftapp2.menu

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.ufrpe.loftapp2.R
import br.ufrpe.loftapp2.adapter.ItemAdapter
import br.ufrpe.loftapp2.model.Item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.food_layout.view.*
import java.util.*

class FoodFragment: Fragment(){

    val itemList:ArrayList<Item> = ArrayList()
    var listAdapter: ItemAdapter? = null

    var mAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        listAdapter = ItemAdapter(itemList, requireContext(), 1)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = LayoutInflater.from(container?.context).inflate(R.layout.food_layout, container, false)
        v.rvItemList.addItemDecoration(SpaceItemDecoration(30, 50, 30))
        v.rvItemList.layoutManager = GridLayoutManager(context, 2)
        v.rvItemList.adapter = listAdapter
        loadFoodItemFirebase()
        return v
    }

    private fun loadFoodItem(){
        itemList.add(Item("1","Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("2","Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("3","Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("4","Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("5","Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("6","Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("7","Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("8","Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("9","Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("10","Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("11","Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("12","Pastel de forno ", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("13","Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("14","Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("15","Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("16","Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("17","Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("18","Coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("19","Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("20","Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        //this.saveItem("Pão de queijo", 6, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/PaoDeQueijo.jpg?alt=media&token=968f1e8c-d115-4f77-a4fe-569997087ada")
        //this.saveItem("coxinha", 10, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/coxinha.jpg?alt=media&token=75fe249f-1b22-4bc1-a52a-02c640af62ff")
        //this.saveItem("Brigadeiro", 10, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/brigadeiro.jpg?alt=media&token=bba9e217-15d1-4b6a-b55c-dc6ade48178c")
        //this.saveItem("Pastel de forno", 6, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/PastelDeForno.jpg?alt=media&token=58a37673-0141-4422-b437-3ab7b69d597c")


    }

    private fun loadFoodItemFirebase(){
        val foodRef = firebaseDatabase!!.getReference("foods")

        foodRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                itemList.clear()

                for(snapshot in p0.children){
                    val hashMap = snapshot.value as HashMap<String, String>
                    if(hashMap.size > 0){
                        val item = Item( hashMap["id"].toString(), hashMap["name"].toString(), hashMap["units"].toString().toInt(),  hashMap["price"].toString().toDouble(), hashMap["ingredients"].toString() , hashMap["imageLink"].toString())
                        itemList.add(item)
                    }
                }

                listAdapter!!.notifyDataSetChanged()

            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Problem reading from server", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun saveItem(name:String, units: Int, price: Double, ingredients:String, imageLink: String  ){
        val ref = firebaseDatabase!!.getReference("foods")
        val foodId:String = ref.push().key!!
        val foodItem = Item(foodId, name, units, price, ingredients, imageLink)

        ref.child(foodId).setValue(foodItem).addOnCompleteListener {
            Toast.makeText(context, "Food salva com sucesso", Toast.LENGTH_LONG ).show()
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