package org.wit.placemark.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.placemark.helpers.exists
import org.wit.placemark.helpers.read
import org.wit.placemark.helpers.write
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
      deserialize()
    }
  }


  override fun findAll(): MutableList<UserModel> {
    return users
  }

  override fun create(user: UserModel) {
    users.add(user)
    serialize()
  }

  override fun update(user: UserModel) {

  }

  private fun serialize() {
    val jsonString = UgsonBuilder.toJson(users, UlistType)
    write(context, UJSON_FILE, jsonString)
  }

  private fun deserialize() {
    val jsonString = read(context, UJSON_FILE)
    users = Gson().fromJson(jsonString, UlistType)
  }
}