package org.wit.placemark.activities.login

import org.wit.placemark.activities.base.BasePresenter
import org.wit.placemark.activities.base.BaseView
import org.wit.placemark.activities.base.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view) {

  fun doLogin(email: String, password: String) {
    view?.navigateTo(VIEW.LIST)
  }

  fun doSignUp(email: String, password: String) {
    view?.navigateTo(VIEW.LIST)
  }
}