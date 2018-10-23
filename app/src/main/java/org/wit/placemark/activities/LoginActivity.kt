package org.wit.placemark.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import org.jetbrains.anko.toast
import org.wit.placemark.R



class LoginActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    var et_user_name = findViewById(R.id.et_user_name) as EditText
    var et_password = findViewById(R.id.et_password) as EditText
    var btn_reset = findViewById(R.id.btn_reset) as Button
    var btn_submit = findViewById(R.id.btn_submit) as Button

    btn_reset.setOnClickListener {
      et_user_name.setText("")
      et_password.setText("")
    }

    btn_submit.setOnClickListener {
      val user_name = et_user_name.text
      val password = et_password.text

      var v_user_name = "admin"
      var v_password = "admin"

      if (user_name.toString() == v_user_name && password.toString() == v_password){
        val intent = Intent(this, PlacemarkListActivity::class.java)
        Toast.makeText(this@LoginActivity, user_name, Toast.LENGTH_LONG).show()
        startActivity(intent)
        finish()
      }
      else{
        toast("Incorrect password or username")
      }
    }
  }
}