package com.alisabet.firstrun

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkFirstRun()
    }

    private fun checkFirstRun() {

        val pref_name = "MyPrefsFile"
        val pref_version_code_key = "version_code"
        val not_exist = -1

        val currentVersionCode = BuildConfig.VERSION_CODE

        // Get saved version code
        val prefs = getSharedPreferences(pref_name, Context.MODE_PRIVATE)
        val savedVersionCode = prefs.getInt(pref_version_code_key, not_exist)

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {
            // This is just a normal run
            Toast.makeText(this, "normal run", Toast.LENGTH_SHORT).show()
        } else if (savedVersionCode == not_exist) {
            //This is a new install (or the user cleared the shared preferences)
            Toast.makeText(this, "new install", Toast.LENGTH_SHORT).show()
        } else if (currentVersionCode > savedVersionCode) {
            //This is an upgrade
            Toast.makeText(this, "an upgrade", Toast.LENGTH_SHORT).show()
        }
        // Update the shared preferences with the current version code
        prefs.edit().putInt(pref_version_code_key, currentVersionCode).commit()
    }

}
