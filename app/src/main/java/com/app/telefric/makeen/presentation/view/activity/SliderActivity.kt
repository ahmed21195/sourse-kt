package com.app.telefric.makeen.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.app.telefric.R
import com.app.telefric.makeen.presentation.view.adapter.ViewPagerAdapter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_slider.*
import kotlinx.android.synthetic.main.activity_slider.view.*


class SliderActivity : AppCompatActivity() {
    private lateinit var adapter: ViewPagerAdapter
    var postion=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        getFirebaseToken()
        getSliderImage()


    }
    fun getSliderImage(){
        var imgs = listOf<Int>(R.drawable.girelmask,R.drawable.nurse,R.drawable.bus)
      //  var tvContient = listOf<String>(getString(R.string.tv1),getString(R.string.tv1),getString(R.string.tv1))
         adapter = ViewPagerAdapter(imgs,this)
        view_pager.adapter = adapter
        worm_dots_indicator.setViewPager(view_pager)

        view_pager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                postion=position
                if (position==0){
                    next_btn.visibility= View.VISIBLE
                    skip.visibility= View.VISIBLE
                    next_finish.visibility= View.GONE
                    tv_data.text=getString(R.string.tv1)
                }else if(position==1){
                    next_btn.visibility= View.VISIBLE
                    skip.visibility= View.VISIBLE
                    next_finish.visibility= View.GONE
                    tv_data.text=getString(R.string.tv2)
                }else if (position==2){
                    next_btn.visibility= View.GONE
                    skip.visibility= View.GONE
                    next_finish.visibility= View.VISIBLE
                    tv_data.text=getString(R.string.tv3)
                }

            }
            override fun onPageSelected(position: Int) {

            }

        })
        next_finish.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        skip.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        next_btn.setOnClickListener{
            view_pager.setCurrentItem(getItem(+1), true) //getItem(-1) for previous


        }
    }
    private fun getItem(i: Int): Int {
        return view_pager.currentItem + i
    }
    fun getFirebaseToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {

                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            Log.d("Token", token.toString())

        })
    }
}