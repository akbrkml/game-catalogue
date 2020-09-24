package com.badrun.gamecatalogue.search

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.badrun.core.data.Resource
import com.badrun.core.ui.GameListAdapter
import com.badrun.gamecatalogue.R
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var adapter: GameListAdapter

    private var navController: NavController? = null

    private val handler = Handler()
    private var runnable: Runnable? = null

    private var currentSearchText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigation.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { game ->
                val action =
                    SearchFragmentDirections.searchFragmentToDetailFragment(gameId = game.gameId)
                navController?.navigate(action)
            }
        })

        viewModel.resultGame.observe(viewLifecycleOwner, { gameResources ->
            if (gameResources != null) {
                when (gameResources) {
                    is Resource.Loading -> {
                        pb_loading.visibility = View.VISIBLE
                        view_empty.visibility = View.GONE
                        view_error.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        pb_loading.visibility = View.GONE
                        if (gameResources.data?.isNotEmpty() == true) {
                            adapter.setData(gameResources.data)
                            view_empty.visibility = View.GONE
                        } else {
                            view_empty.visibility = View.VISIBLE
                            val tvEmpty = activity?.findViewById<AppCompatTextView>(
                                com.badrun.core.R.id.tv_empty
                            )
                            tvEmpty?.text = "Pencarian $currentSearchText tidak ditemukan"
                        }
                    }
                    is Resource.Error -> {
                        pb_loading.visibility = View.GONE
                        view_empty.visibility = View.GONE
                        view_error.visibility = View.VISIBLE
                        val tvError = activity?.findViewById<AppCompatTextView>(
                            com.badrun.core.R.id.tv_error
                        )
                        tvError?.text = gameResources.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        setupRVGame()

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                currentSearchText = newText
                if (runnable != null) {
                    handler.removeCallbacks(runnable!!)
                }
                runnable = Runnable {
                    viewModel.setQuery(currentSearchText)
                }
                handler.postDelayed(runnable!!, 500)
                return false
            }

        })
    }

    private fun setupRVGame() {
        adapter = GameListAdapter()
        adapter.onItemClick = { selectedData ->
            viewModel.navigateTo(selectedData)
        }

        rv_game_results.layoutManager = GridLayoutManager(requireContext(), 3)
        rv_game_results.adapter = adapter
        rv_game_results.isNestedScrollingEnabled = false
    }

}