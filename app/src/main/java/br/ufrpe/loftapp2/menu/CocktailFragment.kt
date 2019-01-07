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

class CocktailFragment: Fragment(){

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
        val v = LayoutInflater.from(container?.context).inflate(R.layout.cocktail_layout, container, false)
        v.rvItemList.addItemDecoration(SpaceItemDecoration(30, 50, 30))
        v.rvItemList.layoutManager = GridLayoutManager(context, 2)
        v.rvItemList.adapter = listAdapter
        loadCocktailsFromFirebase()
        return v
    }
    /*
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

        this.saveItem("Sex on The Beach", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/sexOnTheBeach.jpg?alt=media&token=16d48041-312c-44ed-82bd-685af2202a13")
        this.saveItem("Margarita", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/margarita.jpg?alt=media&token=6e50f1aa-294f-4322-9bbf-0d051f1e5261")
        this.saveItem("White Russian", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/whiteRussian.jpg?alt=media&token=9af57baa-1c5f-4395-a0df-82864624ef4a")
        this.saveItem("Lemon Drop", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/lemonDrop.jpg?alt=media&token=244d2a6f-f722-491a-83cd-43f6a8731bc6")

        this.saveItem("Gimlet", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Negroni", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Manhattan", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Mimosa", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")

        this.saveItem("Sazerac", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Sidecar", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Martini", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Daiquiri", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")

        this.saveItem("Tuthilltown Toddy", 1, 5.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Cinnamon Tequila Hot Toddy", 1, 12.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Prince Pum King", 1, 8.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")
        this.saveItem("Docteur du Miel", 1, 10.0, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "https://firebasestorage.googleapis.com/v0/b/loftapp-5ed42.appspot.com/o/cocktail.jpg?alt=media&token=6d369351-f04c-48d3-9eef-99a88f14b623")


    }
    */
    private fun loadCocktailsFromFirebase(){
        val cocktailRef = firebaseDatabase!!.getReference("cocktails")

        cocktailRef.addValueEventListener(object: ValueEventListener {
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
        val ref = firebaseDatabase!!.getReference("cocktails")
        val cocktailId:String = ref.push().key!!
        val cocktailItem = Item(cocktailId, name, units, price, ingredients, imageLink)

        ref.child(cocktailId).setValue(cocktailItem).addOnCompleteListener {
            Toast.makeText(context, "cocktail salva com sucesso", Toast.LENGTH_LONG ).show()
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