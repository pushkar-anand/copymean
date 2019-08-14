package me.pushkaranand.copymeanx.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.pushkaranand.copymeanx.R
import me.pushkaranand.copymeanx.utils.FirstRunHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstRunHelper = FirstRunHelper(application)

        if (firstRunHelper.isFirstRun()) {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
