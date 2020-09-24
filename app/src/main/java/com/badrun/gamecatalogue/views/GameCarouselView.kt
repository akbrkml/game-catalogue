package com.badrun.gamecatalogue.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.model.ItemViewModel
import com.badrun.core.ui.GameCarouselAdapter
import com.badrun.core.utils.CirclePagerIndicatorDecoration
import com.badrun.gamecatalogue.home.HomeViewModel
import kotlinx.android.synthetic.main.game_carousel_view.view.*

class GameCarouselView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    data class Model(
        override val id: String,
        override val data: Any,
        val withPlaceholder: Boolean
    ) : ItemViewModel

    private val appContext = context.applicationContext

    private lateinit var adapter: GameCarouselAdapter

    fun setContent(value: Any, withPlaceholder: Boolean, vm: HomeViewModel) {
        val listData = value as List<Game>
        adapter = GameCarouselAdapter()
        adapter.setData(listData.reversed().take(5))
        adapter.onItemClick = { selectedData ->
            vm.navigateTo(selectedData)
        }

        rv_game_carousel.layoutManager = LinearLayoutManager(appContext, LinearLayoutManager.HORIZONTAL, false)
        rv_game_carousel.adapter = adapter
        rv_game_carousel.addItemDecoration(CirclePagerIndicatorDecoration())
        val pagerSnapHelper = PagerSnapHelper()
        rv_game_carousel.onFlingListener = null
        pagerSnapHelper.attachToRecyclerView(rv_game_carousel)
    }

}