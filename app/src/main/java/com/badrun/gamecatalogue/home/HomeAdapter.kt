package com.badrun.gamecatalogue.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badrun.core.domain.model.ItemViewModel
import com.badrun.core.utils.BaseViewHolder
import com.badrun.gamecatalogue.R
import com.badrun.gamecatalogue.views.GameCarouselView
import com.badrun.gamecatalogue.views.GameListView
import java.util.*

class HomeAdapter(
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<BaseViewHolder>() {

    private var listData = ArrayList<ItemViewModel>()

    fun setData(newListData: List<ItemViewModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType) {
            0 -> GameCarouselHolder(parent)
            1 -> GameListHolder(parent)
            else -> LoadingHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (getItemViewType(position) != 99) {
            when (holder) {
                is GameCarouselHolder -> {
                    (holder.itemView as GameCarouselView).apply {
                        val model = listData[position]
                        setContent(model.data, false, viewModel)
                    }
                }
                is GameListHolder -> {
                    (holder.itemView as GameListView).apply {
                        val model = listData[position]
                        setContent(model.data, false, viewModel)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    override fun getItemViewType(position: Int): Int =
        when (val data = listData[position]) {
            is GameCarouselView.Model -> 0
            is GameListView.Model -> 1
            else -> 99 // loading model
        }

}

class GameCarouselHolder(
    parent: ViewGroup
) : BaseViewHolder(parent, R.layout.game_carousel_view)


class GameListHolder(
    parent: ViewGroup
) : BaseViewHolder(parent, R.layout.game_list_view)

class LoadingHolder(
    parent: ViewGroup
) : BaseViewHolder(parent, R.layout.item_load_more)