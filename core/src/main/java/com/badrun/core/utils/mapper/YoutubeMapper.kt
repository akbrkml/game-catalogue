package com.badrun.core.utils.mapper

import com.badrun.core.data.source.local.entity.YoutubeEntity
import com.badrun.core.data.source.remote.response.YoutubeResponse
import com.badrun.core.domain.model.Youtube

object YoutubeMapper {
    fun mapResponsesToEntities(input: List<YoutubeResponse>, gameId: Int): List<YoutubeEntity> {
        val youtubeList = ArrayList<YoutubeEntity>()
        input.map {
            val tourism = YoutubeEntity(
                youtubeId = it.id,
                gameId = gameId,
                externalId = it.externalId ?: "",
                name = it.name,
                channelId = it.channelId ?: "",
                channelTitle = it.channelTitle ?: ""
            )
            youtubeList.add(tourism)
        }
        return youtubeList
    }

    fun mapEntitiesToDomain(input: List<YoutubeEntity>): List<Youtube> =
        input.map {
            Youtube(
                youtubeId = it.youtubeId,
                gameId = it.gameId,
                videoId = it.externalId,
                name = it.name,
                channelTitle = it.channelTitle,
                channelId = it.channelId
            )
        }

}