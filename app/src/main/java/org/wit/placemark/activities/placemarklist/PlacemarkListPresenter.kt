package org.wit.placemark.activities.placemarklist

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.wit.placemark.activities.base.BasePresenter
import org.wit.placemark.activities.base.BaseView
import org.wit.placemark.activities.base.VIEW
import org.wit.placemark.models.PlacemarkModel


class PlacemarkListPresenter(view: BaseView) : BasePresenter(view) {

  fun doAddPlacemark() {
    view?.navigateTo(VIEW.PLACEMARK)
  }

  fun doEditPlacemark(placemark: PlacemarkModel) {
    view?.navigateTo(VIEW.PLACEMARK, 0, "placemark_edit", placemark)
  }

  fun doShowPlacemarksMap() {
    view?.navigateTo(VIEW.MAPS)
  }

  fun loadPlacemarks() {
    async(UI) {
      view?.showPlacemarks(app.placemarks.findAll())
    }
  }
}