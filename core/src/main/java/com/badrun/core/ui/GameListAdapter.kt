package com.badrun.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badrun.core.R
import com.badrun.core.domain.model.Game
import com.badrun.core.utils.ext.formatted
import com.badrun.core.utils.ext.load
import kotlinx.android.synthetic.main.item_game_list.view.*
import java.util.ArrayList

class GameListAdapter : RecyclerView.Adapter<GameListAdapter.GameListViewHolder>() {

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
    ): GameListAdapter.GameListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game_list, parent, false)
        return GameListViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameListAdapter.GameListViewHolder, position: Int) {
        val data = listData[position]
        holder.bindView(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(game: Game) {
            with(itemView) {
                iv_game_image.load(game.backgroundImage)
                tv_game_title.text = game.name
                tv_game_date_released.text = game.dateReleased.formatted()
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}