package com.app.telefric.makeen.presentation.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.app.telefric.R
import com.app.telefric.makeen.presentation.view.fragment.DrawerFragment
import com.app.telefric.makeen.presentation.view.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main_screen.*


class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
            .commit()
        openDrawer()
    }
    private fun openDrawer() {
        menu.setOnClickListener {
            drawer.openDrawer(GravityCompat.END);
        }
        (supportFragmentManager.findFragmentById(R.id.nv_fragment) as DrawerFragment?)?.setUp(
            findViewById<View>(R.id.drawer) as DrawerLayout
        )

    }
}