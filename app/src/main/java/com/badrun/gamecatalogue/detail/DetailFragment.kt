package com.badrun.gamecatalogue.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.badrun.core.data.Resource
import com.badrun.core.domain.model.Game
import com.badrun.core.utils.CirclePagerIndicatorDecoration
import com.badrun.core.utils.ext.formatted
import com.badrun.core.utils.ext.load
import com.badrun.gamecatalogue.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()

    private var gameId = 0

    private lateinit var adapter: ScreenshotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameId = DetailFragmentArgs.fromBundle(it).gameId
            viewModel.setGameId(gameId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRVScreenshot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.game.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    it.data?.let { game -> populateDataGame(game) }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.youtube.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val videoId = it.data?.firstOrNull()?.videoId ?: return@observe
                    setupYoutubePlayerView(videoId)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.screenshot.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    adapter.setData(it.data)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.store.observe(viewLifecycleOwner, { storeResources ->
            when (storeResources) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Log.e("TAG", "onActivityCreated: ${storeResources.data}", )
                    btn_game_store.setOnClickListener {
                        val url = storeResources.data?.firstOrNull()?.url ?: return@setOnClickListener
                        openBrowser(url)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun openBrowser(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = uri
        }
        startActivity(intent)
    }

    private fun populateDataGame(game: Game) {
        iv_game_image_detail.load(game.backgroundImage)
        tv_game_title.text = game.name
        tv_game_description.text =
            HtmlCompat.fromHtml(game.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        tv_game_date_released.text = game.dateReleased.formatted()
        rb_game_rating.rating = game.rating.toFloat()
        tv_game_total_reviews.text = "${game.reviewsCount} reviews"

        var statusFavorite = game.isFavorite
        setStatusFavorite(statusFavorite)
        fab_game_favorite.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavoriteGame(game, statusFavorite)
            setStatusFavorite(statusFavorite)
        }
    }

    private fun setupYoutubePlayerView(videoId: String) {
        viewLifecycleOwner.lifecycle.addObserver(youtube_player_view)
        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(videoId, 0F)
                youTubePlayer.pause()
            }
        })
    }

    private fun setupRVScreenshot() {
        adapter = ScreenshotAdapter()
        rv_game_screenshot.adapter = adapter
        rv_game_screenshot.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_game_screenshot.addItemDecoration(CirclePagerIndicatorDecoration())
        val pagerSnapHelper = PagerSnapHelper()
        rv_game_screenshot.onFlingListener = null
        pagerSnapHelper.attachToRecyclerView(rv_game_screenshot)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab_game_favorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_heart_solid))
        } else {
            fab_game_favorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_heart_line))
        }
    }

}