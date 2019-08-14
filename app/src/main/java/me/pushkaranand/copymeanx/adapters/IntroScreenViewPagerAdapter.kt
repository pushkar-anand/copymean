package me.pushkaranand.copymeanx.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import me.pushkaranand.copymeanx.R

class IntroScreenViewPagerAdapter(val context: Context) : PagerAdapter() {

    val layouts: Array<Int> =
        arrayOf(
            R.layout.slide_screen1,
            R.layout.slide_screen2,
            R.layout.slide_screen3
        )

    override fun instantiateItem(container: ViewGroup, position: Int): View {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(layouts[position], container, false)
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}