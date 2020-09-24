package com.badrun.gamecatalogue.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.model.ItemViewModel
import com.badrun.core.ui.GameListAdapter
import com.badrun.gamecatalogue.home.HomeViewModel
import kotlinx.android.synthetic.main.game_list_view.view.*

class GameListView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    data class Model(
        override val id: String,
        override val data: Any,
        val withPlaceholder: Boolean
    ) : ItemViewModel

    private val appContext = context.applicationContext

    private lateinit var adapter: GameListAdapter

    fun setContent(value: Any, withPlaceholder: Boolean, vm: HomeViewModel) {
        val listData = value as List<Game>
        adapter = GameListAdapter()
        adapter.setData(listData)
        adapter.onItemClick = { selectedData ->
            vm.navigateTo(selectedData)
        }

        rv_game_list.layoutManager = GridLayoutManager(appContext, 2)
        rv_game_list.adapter = adapter
        rv_game_list.isNestedScrollingEnabled = false
    }

}