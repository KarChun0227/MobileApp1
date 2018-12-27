package org.wit.placemark.activities.placemark

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.activities.base.BaseView
import org.wit.placemark.helpers.readImageFromPath
import org.wit.placemark.models.PlacemarkModel


class PlacemarkView : BaseView(), AnkoLogger {

  lateinit var presenter: PlacemarkPresenter
  var placemark = PlacemarkModel()

  var check = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark)

    super.init(toolbar, true)

    presenter = initPresenter (PlacemarkPresenter(this)) as PlacemarkPresenter

    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync {
      presenter.doConfigureMap(it)
      it.setOnMapClickListener { presenter.doSetLocation() }
    }

    chooseImage.setOnClickListener { presenter.doSelectImage() }
  }

  override fun showPlacemark(placemark: PlacemarkModel) {
    placemarkTitle.setText(placemark.title)
    description.setText(placemark.description)
    Glide.with(this).load(placemark.image).into(placemarkImage)
    if (placemark.image != null) {
      chooseImage.setText(R.string.change_placemark_image)
    }
    lat.setText("%.6f".format(placemark.location.lat))
    lng.setText("%.6f".format(placemark.location.lng))
    ratingBar.setRating(placemark.rating)
    if(placemark.like == true){likeIcon.setChecked(true)}
  }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
      menuInflater.inflate(R.menu.menu_placemark, menu)
      return super.onCreateOptionsMenu(menu)
    }


  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    checkbox()
    when (item?.itemId) {
      R.id.item_delete -> {
        presenter.doDelete()
      }
      R.id.item_save -> {
        if (placemarkTitle.text.toString().isEmpty()) {
          toast(R.string.enter_placemark_title)
        } else {
          presenter.doAddOrSave(placemarkTitle.text.toString(), description.text.toString(), ratingBar.getRating(), check)
        }
      }
    }
    return super.onOptionsItemSelected(item)
  }

  fun checkbox(){
    if (likeIcon.isChecked()){
      check = true
    }
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (data != null) {
      presenter.doActivityResult(requestCode, resultCode, data)
    }
  }

  override fun onBackPressed() {
    presenter.doCancel()
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
    presenter.doResartLocationUpdates()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }
}

