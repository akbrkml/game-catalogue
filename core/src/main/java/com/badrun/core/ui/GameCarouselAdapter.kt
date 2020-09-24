package com.badrun.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badrun.core.R
import com.badrun.core.domain.model.Game
import com.badrun.core.utils.ext.load
import kotlinx.android.synthetic.main.item_game_carousel.view.*
import java.util.*

class GameCarouselAdapter : RecyclerView.Adapter<GameCarouselAdapter.GameCarouselViewHolder>() {

    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null

    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameCarouselAdapter.GameCarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game_carousel, parent, false)
        return GameCarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameCarouselAdapter.GameCarouselViewHolder, position: Int) {
        val data = listData[position]
        holder.bindView(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class GameCarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(game: Game) {
            with(itemView) {
                iv_game_image_carousel.load(game.backgroundImage)
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}