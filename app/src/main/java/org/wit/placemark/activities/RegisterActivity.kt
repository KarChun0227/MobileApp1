package org.wit.placemark.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.UserJSONStore
import org.wit.placemark.models.UserModel
import org.wit.placemark.models.UserStore

class RegisterActivity : AppCompatActivity() {

  lateinit var users: UserStore

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)

    var user = UserModel()

    btn_reset_register.setOnClickListener {
      et_user_name_register.setText("")
      et_password_register.setText("")
    }

    btn_submit_register.setOnClickListener {
      user.Email = et_user_name_register.text.toString()
      user.Password = et_password_register.text.toString()
      if (user.Email.isEmpty() && user.Password.isEmpty()) {
        toast("Email or Password not entry")
      } else {
        users.user_create(user.copy())
      }
      startActivity(Intent(this, LoginActivity::class.java))
    }
  }
}