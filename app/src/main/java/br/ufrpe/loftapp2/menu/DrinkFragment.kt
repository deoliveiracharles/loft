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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.food_layout.view.*
import java.util.HashMap

class DrinkFragment: Fragment(){

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
        val v = LayoutInflater.from(container?.context).inflate(R.layout.drink_layout, container, false)
        v.rvItemList.addItemDecoration(SpaceItemDecoration(30, 50, 30))
        v.rvItemList.layoutManager = GridLayoutManager( context, 2)
        v.rvItemList.adapter = listAdapter
        loadDrinkFromFirebase()
        return v
    }
    /*
    private fun loadDrinkItem(){
        itemList.add(Item("1","Jack Daniel's", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("2","Red Label", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("3","Black Label", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("4","Black&White", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("5","Jose Cuervo", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("6","Patron", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("7","1800 Reposado", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("8","Don Julio", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("9","Blue Label", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("10","Old Parr", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("11","Chivas Regal", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("12","Gold Label", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("13","Ciroc", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("14","Absolut", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("15","AnestasiA", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("16","Absolut Elyx", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        itemList.add(Item("17","Chandon", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("18","Freixenet", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("19","Canevari", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))
        itemList.add(Item("20","Bellini", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", ""))

        /*
        this.saveItem("Jack Daniel's", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/jackdeniels.jpg?alt=media&token=74b73bd8-b68d-4b4e-9558-42b8ba882172")
        this.saveItem("Red Label", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/redLabel.jpg?alt=media&token=59b22476-5eea-4d7d-8597-fbb764c11408")
        this.saveItem("Black Label", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/blackLabel.jpg?alt=media&token=142ff6fb-f98d-4843-9088-9d5891ba60a4")
        this.saveItem("Black&White", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/blackWhite.jpg?alt=media&token=da8f1ce9-a86b-4953-8586-9bc7fb2ef516")

        this.saveItem("Jose Cuervo", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("1800 Reposado", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Don Julio", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Patron", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")

        this.saveItem("Blue Label", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Old Parr", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Chivas Regal", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Gold Label", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")

        this.saveItem("Ciroc", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Absolut", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("AnestasiA", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Absolut Elyx", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")

        this.saveItem("Chandon", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Freixenet", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Canevari", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        this.saveItem("Bellini 2000", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/bebida.jpg?alt=media&token=bfdd6b91-cdfe-4e5d-9de7-41b109047b71")
        */

    }
*/
    private fun loadDrinkFromFirebase(){
        val drinkRef = firebaseDatabase!!.getReference("drinks")

        drinkRef.addValueEventListener(object: ValueEventListener {
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
    /*
    private fun saveItem(name:String, units: Int, price: Double, ingredients:String, imageLink: String  ){
        val ref = firebaseDatabase!!.getReference("drinks")
        val drinkId:String = ref.push().key!!
        val drinkItem = Item(drinkId, name, units, price, ingredients, imageLink)

        ref.child(drinkId).setValue(drinkItem).addOnCompleteListener {
            Toast.makeText(context, "drink salva com sucesso", Toast.LENGTH_LONG ).show()
        }
    }
*/
    inner class SpaceItemDecoration(val spaceLeft: Int, val spaceTop: Int, val spaceRight: Int ): RecyclerView.ItemDecoration(){
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = spaceTop
            outRect.left = spaceLeft
            outRect.right = spaceRight
        }
    }
}