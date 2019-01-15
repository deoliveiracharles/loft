package br.ufrpe.loftapp2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.ufrpe.loftapp2.card.CardFragment
import br.ufrpe.loftapp2.credentials.Login
import br.ufrpe.loftapp2.menu.MenuFragment
import br.ufrpe.loftapp2.model.Item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.HashMap


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var currentlyFragment: Fragment? = null

    val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        */
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if(savedInstanceState != null){
            currentlyFragment = supportFragmentManager.getFragment(savedInstanceState,
                savedInstanceState.getCharSequence("tag") as String
            )
        }else{
            loadMenu(MenuFragment())
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if(outState != null){
            currentlyFragment = getCurrentFragment()
            outState.putCharSequence("tag", currentlyFragment!!.tag)
            supportFragmentManager.putFragment(outState, currentlyFragment!!.tag!!, currentlyFragment!!)
        }
    }
    /*override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }*/

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        user_name.text = user?.displayName
        user_email.text = user?.email
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_menu -> {
                loadMenu(MenuFragment())
            }
            R.id.nav_card -> {
                loadCard(CardFragment())
            }
            R.id.nav_exit -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, Login::class.java )
                startActivity(intent)
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadMenu(fragment:MenuFragment){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.frameLayout, fragment, "menuFragment")
        fragmentManager.commit()

    }

    private fun loadCard(fragment:CardFragment){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.frameLayout, fragment, "cardFragment")
        fragmentManager.commit()
    }

    private fun getCurrentFragment(): Fragment? {
        var fragmentList:List<Fragment> = supportFragmentManager.fragments
        if(fragmentList != null){
            for(fragment:Fragment in fragmentList){
                if(fragment != null && fragment.isVisible) {
                    var tag = fragment.tag
                    return fragment
                }
            }
        }
        return null
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.RequestCodeComidas && resultCode == Activity.RESULT_OK){
            val item = data?.getSerializableExtra("item") as Item
            Constants.card.add(item)
            val resString = getString(R.string.addedToCard)
            Toast.makeText(this,"" +Constants.card[Constants.card.size - 1].name + " " + resString, Toast.LENGTH_LONG).show()
        }
        /*
        if(requestCode == Constants.RequestCodeDelete && resultCode == Activity.RESULT_OK){
            val item = data?.getSerializableExtra("item") as Item
            val position = data?.getIntExtra("position", -1)
            Constants.card.removeAt(position)
            //rvCardList.adapter!!.notifyItemRemoved(position)
            //rvCardList.adapter!!.notifyItemRangeChanged(position, Constants.card.size)
            //updatePrice()
            Toast.makeText(this,"Item Deletado" , Toast.LENGTH_LONG).show()
        }
        */
    }


}
