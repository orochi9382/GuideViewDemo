package com.lala.guidview.guidsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class AAA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aaa)

        val transaction = supportFragmentManager.beginTransaction()
        val homeFragment = FragmentGuide()
        transaction.add(R.id.framgeLayout, homeFragment)
        transaction.commit()
    }
}