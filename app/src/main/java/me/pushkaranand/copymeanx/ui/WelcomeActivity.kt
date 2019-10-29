package me.pushkaranand.copymeanx.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_welcome.*
import me.pushkaranand.copymeanx.R
import me.pushkaranand.copymeanx.adapters.IntroScreenViewPagerAdapter
import me.pushkaranand.copymeanx.utils.FirstRunHelper

class WelcomeActivity : AppCompatActivity() {

    private var introViewPagerAdapter: IntroScreenViewPagerAdapter? = null
    private var introBullets: Array<TextView>? = null

    private var firstRunHelper: FirstRunHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        firstRunHelper = FirstRunHelper(application)
        if (!firstRunHelper!!.isFirstRun()) {
            startApp()
        }

        introViewPagerAdapter = IntroScreenViewPagerAdapter(this)


        // adding bottom introBullets
        makeBottomBullets(0)



        introViewPager!!.adapter = introViewPagerAdapter
        introViewPager!!.addOnPageChangeListener(introViewPagerListener)


        skipBtn.setOnClickListener {
            startApp()
        }

        nextBtn.setOnClickListener {
            // checking for last page
            // if last page home screen will be launched
            val current = introViewPager.currentItem + 1
            if (current < introViewPagerAdapter!!.layouts.size) {
                // move to next screen
                introViewPager!!.currentItem = current
            } else {
                startApp()
            }
        }

        // making notification bar transparent
        setTransparentStatusBar()
    }

    private fun makeBottomBullets(currentPage: Int) {

        val arraySize = introViewPagerAdapter!!.layouts.size

        introBullets = Array(arraySize) {
            TextView(this)
        }

        val colorsActive = resources.getIntArray(R.array.array_intro_bullet_active)
        val colorsInactive = resources.getIntArray(R.array.array_intro_bullet_inactive)

        introBulletsLayout!!.removeAllViews()

        for (i in 0 until introBullets!!.size) {
            introBullets!![i] = TextView(this)
            introBullets!![i].text =
                HtmlCompat.fromHtml("&#9679;", HtmlCompat.FROM_HTML_MODE_LEGACY);
            introBullets!![i].textSize = 30F
            introBullets!![i].setTextColor(colorsInactive[currentPage])
            introBulletsLayout!!.addView(introBullets!![i])
        }

        if (introBullets!!.isNotEmpty())
            introBullets!![currentPage].setTextColor(colorsActive[currentPage])
    }


    private var introViewPagerListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {

                makeBottomBullets(position)

                /*Based on the page position change the button text*/

                if (position == introViewPagerAdapter!!.layouts.size - 1) {
                    nextBtn.text = getString(R.string.next)
                    skipBtn.setVisibility(View.GONE)
                } else {
                    nextBtn.text = getString(R.string.next)
                    skipBtn.setVisibility(View.VISIBLE)
                }
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
                //Do nothing for now
            }

            override fun onPageScrollStateChanged(arg0: Int) {
                //Do nothing for now
            }
        }

    private fun setTransparentStatusBar() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun startApp() {
        firstRunHelper?.firstRunDone()
        val intent = Intent(this, BaseActivity::class.java)
        startActivity(intent)
        finish()
    }

}

