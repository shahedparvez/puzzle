package com.por.sha.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        turnOnStrictMode()
        setContentView(R.layout.activity_main)
    }

    private fun turnOnStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    StrictMode.ThreadPolicy.Builder().detectAll()
                            .penaltyLog().penaltyDeath().build())
            StrictMode.setVmPolicy(
                    StrictMode.VmPolicy.Builder().detectAll()
                            .penaltyLog().penaltyDeath().build())
        }
    }
}
