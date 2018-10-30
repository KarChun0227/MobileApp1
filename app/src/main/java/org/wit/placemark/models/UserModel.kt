package org.wit.placemark.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(var Email: String = "",
                          var Password: String = "") : Parcelable