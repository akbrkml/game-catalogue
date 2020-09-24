package com.badrun.gamecatalogue.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.badrun.core.R
import com.badrun.core.domain.model.Screenshot
import com.badrun.core.utils.ext.load

class ScreenshotAdapter: RecyclerView.Adapter<ScreenshotAdapter.ScreenshotHolder>() {

    private var listData = ArrayList<Screenshot>()
    var onItemClick: ((Screenshot) -> Unit)? = null

    fun setData(newListData: List<Screenshot>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game_carousel, parent, false)
        return ScreenshotHolder(view)
    }

    override fun onBindViewHolder(holder: ScreenshotHolder, position: Int) {
        val data = listData[position]
        holder.bindView(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ScreenshotHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivGameCarousel =
            itemView.findViewById<AppCompatImageView>(com.badrun.core.R.id.iv_game_image_carousel)

        fun bindView(screenshot: Screenshot) {
            ivGameCarousel.load(screenshot.image)
        }
    }
}