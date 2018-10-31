package org.wit.placemark.models

interface UserStore {
  fun user_findAll(): List<UserModel>
  fun user_create(user: UserModel)
  fun user_update(user: UserModel)
}