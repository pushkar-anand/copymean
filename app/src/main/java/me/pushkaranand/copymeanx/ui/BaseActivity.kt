package me.pushkaranand.copymeanx.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.pushkaranand.copymeanx.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
