package me.pushkaranand.copymeanx.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.pushkaranand.copymeanx.R
import me.pushkaranand.copymeanx.utils.FirstRunHelper

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        shouldShowOnBoarding()
    }

    private fun shouldShowOnBoarding() {
        val firstRunHelper = FirstRunHelper(application)
        if (firstRunHelper.isFirstRun()) {
            showOnBoard()
        }
    }

    private fun showOnBoard() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
