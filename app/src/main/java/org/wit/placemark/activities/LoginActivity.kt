package org.wit.placemark.activities

import android.app.IntentService
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.*
import org.wit.placemark.R
import org.wit.placemark.R.id.message


class LoginActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    // get reference to all views
    var et_user_name = findViewById(R.id.et_user_name) as EditText
    var et_password = findViewById(R.id.et_password) as EditText
    var btn_reset = findViewById(R.id.btn_reset) as Button
    var btn_submit = findViewById(R.id.btn_submit) as Button

    btn_reset.setOnClickListener {
      // clearing user_name and password edit text views on reset button click
      et_user_name.setText("")
      et_password.setText("")
    }

    // set on-click listener
    btn_submit.setOnClickListener {
      val user_name = et_user_name.text
      val password = et_password.text

      var v_user_name = "admin"
      var v_password = "admin"

      val intent = Intent(this, PlacemarkListActivity)

      Toast.makeText(this@LoginActivity, user_name, Toast.LENGTH_LONG).show()
      if (user_name.toString() == v_user_name && password.toString() == v_password){startActivity(intent)}
    }
  }
}