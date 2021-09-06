package com.app.telefric.makeen.presentation.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.app.telefric.R
import com.app.telefric.makeen.presentation.view.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_drawer.*


class DrawerFragment : Fragment() {
    private var homNV: TextView? = null

    private var mDrawerToggel: ActionBarDrawerToggle? = null
    private var mDrawerLayout: DrawerLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_drawer, container,
            false)
         homNV = view.findViewById<TextView>(R.id.home)


        drawerBtnClick()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout.setOnClickListener{
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        containerlay.setOnTouchListener(OnTouchListener { v, event -> true })
    }
    fun drawerBtnClick(){

        homNV?.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
                .commit()
            mDrawerLayout!!.closeDrawer(GravityCompat.END)
        }
    }

    //setUp custom navigation
    fun setUp(drawerLayout: DrawerLayout) {
        mDrawerLayout = drawerLayout
        mDrawerToggel = object : ActionBarDrawerToggle(
            activity,
            drawerLayout,
            null,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
        }
        mDrawerLayout!!.addDrawerListener(mDrawerToggel as ActionBarDrawerToggle)
    }


}