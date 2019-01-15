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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.card_layout.*
import kotlinx.android.synthetic.main.card_layout.view.*
import java.util.*

class CardFragment: Fragment(){

    var firebaseDatabase: FirebaseDatabase? = null
    val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseDatabase = FirebaseDatabase.getInstance()
        //loadPreExistingCardFirebase()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = LayoutInflater.from(container?.context).inflate(R.layout.card_layout, container, false)
        v.rvCardList.addItemDecoration(SpaceItemDecoration(0, 40, 0))
        v.rvCardList.layoutManager = LinearLayoutManager(context)
        v.rvCardList.adapter = CardItemAdapter(Constants.card, requireContext(), 2, this)
        v.rvCardList2.addItemDecoration(SpaceItemDecoration(0, 40, 0))
        v.rvCardList2.layoutManager = LinearLayoutManager(context)
        v.rvCardList2.adapter = CardItemAdapter(Constants.confirmedCard, requireContext(), 2, this)
        v.confirmBtn.setOnClickListener {
            val intent = Intent(context, ConfirmCardActivity::class.java)
            this.startActivityForResult(intent, Constants.RequestCodeConfirm)
        }
        updatePrice(v)
        return v
    }

    fun updatePrice(v: View) {
        var cardPrice = 0.0
        for(item in Constants.card){
            cardPrice += item.price
        }
        for(item in Constants.confirmedCard){
            cardPrice += item.price
        }
        v.cardValue.text = cardPrice.toString()
        v.totalValue.text = cardPrice.toString()
        v.itemCount.text = (Constants.card.size + Constants.confirmedCard.size).toString()
    }
    fun updatePrice() {
        var cardPrice = 0.0
        for(item in Constants.card){
            cardPrice += item.price
        }
        for(item in Constants.confirmedCard){
            cardPrice += item.price
        }
        cardValue.text = cardPrice.toString()
        totalValue.text = cardPrice.toString()
        itemCount.text = (Constants.card.size + Constants.confirmedCard.size).toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.RequestCodeDelete && resultCode == Activity.RESULT_OK){
            val item = data?.getSerializableExtra("item") as Item
            val position = data?.getIntExtra("position", -1)
            Constants.card.removeAt(position)
            rvCardList.adapter!!.notifyItemRemoved(position)
            rvCardList.adapter!!.notifyItemRangeChanged(position, Constants.card.size)
            Toast.makeText(context, getString(R.string.itemDel) , Toast.LENGTH_LONG).show()
            updatePrice()
        }

        if(requestCode == Constants.RequestCodeConfirm && resultCode == Activity.RESULT_OK){
            val ref = firebaseDatabase!!.getReference("cards")
            val user =  FirebaseAuth.getInstance().currentUser?.email
            for (i in Constants.card.indices){
                if(!Constants.card[i].isConfirmed){
                    var cardElement:String = ref.push().key!!
                    //var view = rvCardList.layoutManager!!.findViewByPosition(i)
                    Constants.card[i].isConfirmed = true
                    /*
                    if(view!!.findViewById<ImageView>(R.id.ivCardDelete) == null){

                    }else {
                        var button = view!!.findViewById<ImageView>(R.id.ivCardDelete)
                        button.visibility = View.INVISIBLE
                    }
                    */
                    ref.child(cardElement).child("userEmail").setValue(user)
                    ref.child(cardElement).child("item").setValue(Constants.card[i])

                    Constants.confirmedCard.add(Constants.card[i])
                }
            }
            Constants.card.clear()
            rvCardList.adapter!!.notifyDataSetChanged()
            rvCardList2.adapter!!.notifyDataSetChanged()
            this.updatePrice()

        }

    }

    private fun loadPreExistingCardFirebase(){
        val cardRef = firebaseDatabase!!.getReference("cards")
        cardRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                Constants.confirmedCard.clear()

                for(snapshot in p0.children){
                    val hashMap = snapshot.value as HashMap<String, String>
                    if(hashMap.size > 0){
                        if(hashMap["userEmail"].toString() == user!!.email){
                            var hashItem = hashMap["item"] as HashMap<String, String>
                            var item = Item(hashItem["id"].toString(), hashItem["name"].toString(), hashItem["units"].toString().toInt(), hashItem["price"].toString().toDouble(), hashItem["igredients"].toString(), hashItem["imageLink"].toString())
                            item.isConfirmed = true
                            Constants.confirmedCard.add(item)
                        }
                        //val item = Item( hashMap["id"].toString(), hashMap["name"].toString(), hashMap["units"].toString().toInt(),  hashMap["price"].toString().toDouble(), hashMap["ingredients"].toString() , hashMap["imageLink"].toString())
                        //itemList.add(item)
                    }
                }
                rvCardList2.adapter!!.notifyDataSetChanged()
                updatePrice()

            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Problem reading from server", Toast.LENGTH_LONG).show()
            }
        })
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