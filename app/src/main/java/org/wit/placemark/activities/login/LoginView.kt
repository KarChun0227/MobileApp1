package org.wit.placemark.activities.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.activities.base.BaseView

class LoginView : BaseView() {

  lateinit var presenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    progressBar.visibility = View.GONE

    var et_user_name = findViewById(R.id.et_user_name) as EditText
    var et_password = findViewById(R.id.et_password) as EditText
    var btn_reset = findViewById(R.id.btn_reset) as Button
    var btn_submit = findViewById(R.id.btn_submit) as Button

    presenter = initPresenter(LoginPresenter(this)) as LoginPresenter

    btn_register.setOnClickListener {
      val email = et_user_name.text.toString()
      val password = et_password.text.toString()
      if (email == "" || password == "") {
        toast("Please provide email + password")
      }
      else {
        presenter.doSignUp(email,password)
      }
    }

    btn_reset.setOnClickListener {
      et_user_name.setText("")
      et_password.setText("")
    }

    btn_submit.setOnClickListener {
      val email = et_user_name.text.toString()
      val password = et_password.text.toString()
      if (email == "" && password == "") {
        toast("Please provide email + password")
      }
      else {
        presenter.doLogin(email,password)
      }
    }
  }

  override fun showProgress() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgress() {
    progressBar.visibility = View.GONE
  }
}
