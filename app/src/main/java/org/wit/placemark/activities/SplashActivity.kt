package org.wit.placemark.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.wit.placemark.activities.login.LoginView

class SplashActivity : AppCompatActivity(){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val intent = Intent(this, LoginView::class.java)
    startActivity(intent)
    finish()
    }


}