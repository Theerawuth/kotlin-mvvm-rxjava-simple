package com.example.theerawuth_p.kotlin_mvvm_starter.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.theerawuth_p.kotlin_mvvm_starter.R
import com.example.theerawuth_p.kotlin_mvvm_starter.view.UsersListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, UsersListFragment()).commit()
        }
    }
}
