package me.pushkaranand.copymeanx

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_welcome.view.*
import me.pushkaranand.copymeanx.ui.MainActivity

class WelcomeActivity : AppCompatActivity() {
    private var introViewPager: ViewPager? = null
    private var introViewPagerAdapter: IntroScreenViewPagerAdapter? = null
    private var introBullets: Array<TextView>? = null
    private var introBulletsLayout: LinearLayout? = null
    private var introSliderLayouts: IntArray? = null
    private var btnSkip: Button? = null
    var btnNext: Button? = null
    private var runOnce: RunOnce? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val greetLayout = layoutInflater.inflate(R.layout.activity_welcome, null)
        setContentView(greetLayout)

        runOnce = RunOnce(this)

        if (!runOnce!!.isFirstTimeLaunch) {
            applicationStartup()
            finish()
        }

        introViewPager = greetLayout.intro_view_pager
        introBulletsLayout = greetLayout.intro_bullets
        btnSkip = greetLayout.btn_skip
        btnNext = greetLayout.btn_next


        //Get the intro slides
        introSliderLayouts = intArrayOf(
            R.layout.slide_screen1,
            R.layout.slide_screen2,
            R.layout.slide_screen2)

        // adding bottom introBullets
        makeIIntroBullets(0)


        introViewPagerAdapter = IntroScreenViewPagerAdapter()
        introViewPager!!.adapter = introViewPagerAdapter
        introViewPager!!.addOnPageChangeListener(introViewPagerListener)

        (btnSkip as View?)!!.setOnClickListener { applicationStartup() }

        (btnNext as View?)!!.setOnClickListener {
            // checking for last page
            // if last page home screen will be launched
            val current = getItem(+1)
            if (current < introSliderLayouts!!.size) {
                // move to next screen
                introViewPager!!.currentItem = current
            } else {
                applicationStartup()
            }
        }

        // making notification bar transparent
        SetTransperantStatusBar()
    }

    private fun makeIIntroBullets(currentPage: Int) {
        var arraySize = introSliderLayouts!!.size
        introBullets = Array<TextView>(arraySize) { textboxInit() }

        val colorsActive = resources.getIntArray(R.array.array_intro_bullet_active)
        val colorsInactive = resources.getIntArray(R.array.array_intro_bullet_inactive)

        introBulletsLayout!!.removeAllViews()
        for (i in 0 until introBullets!!.size) {
            introBullets!![i] = TextView(this)
            introBullets!![i].text = Html.fromHtml("&#9679;")
            introBullets!![i].setTextSize(30F)
            introBullets!![i].setTextColor(colorsInactive[currentPage])
            introBulletsLayout!!.addView(introBullets!![i])
        }

        if (introBullets!!.size > 0)
            introBullets!![currentPage].setTextColor(colorsActive[currentPage])
    }

    private fun textboxInit(): TextView {
        return TextView(applicationContext)
    }

    private fun getItem(i: Int): Int {
        return introViewPager!!.getCurrentItem() + i
    }

    private fun applicationStartup() {
        runOnce!!.isFirstTimeLaunch = false
        startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        finish()
    }

    private var introViewPagerListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {

            makeIIntroBullets(position)

            /*Based on the page position change the button text*/

            if (position == introSliderLayouts!!.size - 1) {
                btnNext!!.setText(getString(R.string.done_button_title))
                btnSkip!!.setVisibility(View.GONE)
            } else {
                btnNext!!.setText(getString(R.string.next_button_title))
                btnSkip!!.setVisibility(View.VISIBLE)
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
            //Do nothing for now
        }

        override fun onPageScrollStateChanged(arg0: Int) {
            //Do nothing for now
        }
    }

    private fun SetTransperantStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    inner class IntroScreenViewPagerAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater: LayoutInflater = LayoutInflater.from(applicationContext)

            val view = layoutInflater.inflate(introSliderLayouts!![position], container, false)
            container.addView(view)

            return view
        }

        override fun getCount(): Int {
            return introSliderLayouts!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}
    }
}
