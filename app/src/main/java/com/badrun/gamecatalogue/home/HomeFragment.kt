package com.badrun.gamecatalogue.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.badrun.core.data.Resource
import com.badrun.core.domain.model.ItemViewModel
import com.badrun.gamecatalogue.R
import com.badrun.gamecatalogue.views.GameCarouselView
import com.badrun.gamecatalogue.views.GameListView
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private var navController: NavController? = null

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.game.observe(viewLifecycleOwner, { gameResources ->
            if (gameResources != null) {
                when (gameResources) {
                    is Resource.Loading -> {
                        pb_loading.visibility = View.VISIBLE
                        view_error.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        pb_loading.visibility = View.GONE
                        val listData = ArrayList<ItemViewModel>()
                        listData.add(0, GameCarouselView.Model("carousel", gameResources.data!!, false))
                        listData.add(1, GameListView.Model("list", gameResources.data!!, false))
                        adapter.setData(listData)
                    }
                    is Resource.Error -> {
                        pb_loading.visibility = View.GONE
                        view_error.visibility = View.VISIBLE
                        val tvError = activity?.findViewById<AppCompatTextView>(
                            com.badrun.core.R.id.tv_error
                        )
                        tvError?.text = gameResources.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        viewModel.navigation.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { game ->
                val action =
                    HomeFragmentDirections.homeFragmentToDetailFragment(gameId = game.gameId)
                navController?.navigate(action)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        setupRVHome()
    }

    private fun setupRVHome() {
        adapter = HomeAdapter(viewModel)
        rv_home.layoutManager = LinearLayoutManager(requireContext())
        rv_home.adapter = adapter
    }

}