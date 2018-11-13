package br.ufrpe.loftapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter : SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        toolbar.setNavigationIcon(R.drawable.menu_white)
        setSupportActionBar(toolbar)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter

        tabs.setupWithViewPager(container)

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.RequestCodeComidas && resultCode == Activity.RESULT_OK){
            val item = data?.getSerializableExtra("item") as Item
            Constants.card.add(item)
            Toast.makeText(this, """item adicionado ao card ${Constants.card[Constants.card.size - 1].name}""", Toast.LENGTH_LONG).show()

        }
    }

    inner class SectionsPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter (fm) {

        override fun getItem(position: Int): Fragment? {
            return when(position){
                0 -> {
                    ComidasFragment()
                }
                1 -> {
                    BebidasFragment()
                }
                2 -> {
                    DrinksFragment()
                }
                else -> {
                    null
                }
            }
        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position){
                0 -> return resources.getString(R.string.foods)
                1 -> return resources.getString(R.string.beverage)
                2 -> return resources.getString(R.string.drinks)
            }
            return null
        }
    }
}
