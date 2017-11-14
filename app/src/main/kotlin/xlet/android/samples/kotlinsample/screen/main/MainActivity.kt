package xlet.android.samples.kotlinsample.screen.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import xlet.android.samples.kotlinsample.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val redView = View(this)
        redView.setBackgroundColor(Color.RED)
        setContentView(redView)

    }
}