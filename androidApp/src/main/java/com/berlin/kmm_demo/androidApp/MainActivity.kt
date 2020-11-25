package com.berlin.kmm_demo.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.berlin.kmm_demo.shared.Greeting
import android.widget.TextView
import com.berlin.kmm_demo.shared.ApiService

fun greet(): String {
    return Greeting().greeting()
}

fun networkService() {
    val apiService = ApiService()
    apiService.about { htmlString ->
        println("received ${htmlString}")
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
        networkService()
    }
}
