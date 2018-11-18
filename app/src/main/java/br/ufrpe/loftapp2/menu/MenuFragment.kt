package br.ufrpe.loftapp2.menu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.ufrpe.loftapp2.R
import kotlinx.android.synthetic.main.menu_layout.view.*


class MenuFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val x = LayoutInflater.from(container?.context).inflate(R.layout.menu_layout, container, false)
        x.viewPage.adapter = SectionsPagerAdapter(childFragmentManager)
        x.tabs.post { x.tabs.setupWithViewPager(x.viewPage) }
        return x
    }

    internal inner class SectionsPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm){

        override fun getItem(position: Int): Fragment? {
            return when(position){
                0 -> {
                    FoodFragment()
                }
                1 -> {
                    DrinkFragment()
                }
                2 -> {
                    CocktailFragment()
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
                0 -> return resources.getString(R.string.food)
                1 -> return resources.getString(R.string.drink)
                2 -> return resources.getString(R.string.cocktail)
            }
            return null
        }
    }
}