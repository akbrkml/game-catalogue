package com.badrun.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.badrun.core.ui.GameListAdapter
import kotlinx.android.synthetic.main.favorite_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()

    private lateinit var adapter: GameListAdapter

    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigation.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { game ->
                val action =
                    FavoriteFragmentDirections.favoriteFragmentToDetailFragment(gameId = game.gameId)
                navController?.navigate(action)
            }
        })

        viewModel.gameFavorite.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.setData(it)
                view_empty.visibility = View.GONE
            } else {
                view_empty.visibility = View.VISIBLE
                setEmptyMessage()
            }
        })
    }

    private fun setEmptyMessage() {
        val tvEmpty = activity?.findViewById<AppCompatTextView>(
            com.badrun.core.R.id.tv_empty
        )
        tvEmpty?.text = getString(R.string.label_game_favorite_not_found)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        setupRVGameFavorite()
    }

    private fun setupRVGameFavorite() {
        adapter = GameListAdapter()
        adapter.onItemClick = { selectedData ->
            viewModel.navigateTo(selectedData)
        }

        rv_game_favorite.layoutManager = LinearLayoutManager(requireContext())
        rv_game_favorite.adapter = adapter
        rv_game_favorite.isNestedScrollingEnabled = false
    }

}