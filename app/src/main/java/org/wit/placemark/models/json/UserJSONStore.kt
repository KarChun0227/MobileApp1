package org.wit.placemark.models.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.placemark.helpers.exists
import org.wit.placemark.helpers.read
import org.wit.placemark.helpers.write
import org.wit.placemark.models.UserModel
import org.wit.placemark.models.UserStore
import java.util.ArrayList

val UJSON_FILE = "user.json"
val UgsonBuilder = GsonBuilder().setPrettyPrinting().create()
val UlistType = object : TypeToken<ArrayList<UserModel>>() {}.type

class UserJSONStore : UserStore, AnkoLogger {

  val context: Context
  var users = mutableListOf<UserModel>()

  constructor (context: Context) {
    this.context = context
    if (exists(context, JSON_FILE)) {
      user_deserialize()
    }
  }


  override fun user_findAll(): MutableList<UserModel> {
    return users
  }

  override fun user_create(user: UserModel) {
    users.add(user)
    user_serialize()
  }

  override fun user_update(user: UserModel) {

  }



  private fun user_serialize() {
    val jsonString = UgsonBuilder.toJson(users, UlistType)
    write(context, UJSON_FILE, jsonString)
  }

  private fun user_deserialize() {
    val jsonString = read(context, UJSON_FILE)
    users = Gson().fromJson(jsonString, UlistType)
  }
}