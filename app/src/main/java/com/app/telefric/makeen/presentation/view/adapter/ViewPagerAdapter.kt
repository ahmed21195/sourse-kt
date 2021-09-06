package com.app.telefric.makeen.presentation.view.adapter


import android.content.Context


import android.view.LayoutInflater


import android.view.View


import android.view.ViewGroup


import android.widget.ImageView
import android.widget.TextView


import androidx.viewpager.widget.PagerAdapter
import com.app.telefric.R


class ViewPagerAdapter(var list: List<Int>,var ctx: Context) : PagerAdapter() {

    private lateinit var ImgList: List<Int>


    lateinit var layoutInflater: LayoutInflater


    lateinit var context: Context


    override fun getCount(): Int {

        return list.size


    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {


        return view.equals(`object`)


    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        layoutInflater = LayoutInflater.from(ctx)


        var view = layoutInflater.inflate(R.layout.image_slider_item, container, false)


        val img = view.findViewById<ImageView>(R.id.imageSlider)



        img.setImageResource(list[position])
       // tv.text = listTv[position]

        container.addView(view, 0)


        return view


    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {


        container.removeView(`object` as View)

    }


}