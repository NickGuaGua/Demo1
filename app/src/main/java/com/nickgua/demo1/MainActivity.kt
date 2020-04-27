package com.nickgua.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nickgua.demo1.toppage.TopPageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container,
            TopPageFragment()
        ).commit()
    }
}
