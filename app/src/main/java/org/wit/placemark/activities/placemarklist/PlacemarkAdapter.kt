package org.wit.placemark.activities.placemarklist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_placemark.view.*
import org.wit.placemark.R
import com.bumptech.glide.Glide
import org.wit.placemark.helpers.readImageFromPath
import org.wit.placemark.models.PlacemarkModel

interface PlacemarkListener {
  fun onPlacemarkClick(placemark: PlacemarkModel)
}

class PlacemarkAdapter constructor(private var placemarks: List<PlacemarkModel>,
private val listener: PlacemarkListener) : androidx.recyclerview.widget.RecyclerView.Adapter<PlacemarkAdapter.MainHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_placemark, parent, false))
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val placemark = placemarks[holder.adapterPosition]
    holder.bind(placemark, listener)
  }

  override fun getItemCount(): Int = placemarks.size

  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(placemark: PlacemarkModel, listener: PlacemarkListener) {
      itemView.placemarkTitle.text = placemark.title
      itemView.description.text = placemark.description
      Glide.with(itemView.context).load(placemark.image).into(itemView.imageIcon)
      if (placemark.like.equals(true)){
        itemView.likeIcon.setChecked(true)
      }else{
        itemView.likeIcon.setChecked(false)
      }
      itemView.setOnClickListener { listener.onPlacemarkClick(placemark) }
    }
  }
}
