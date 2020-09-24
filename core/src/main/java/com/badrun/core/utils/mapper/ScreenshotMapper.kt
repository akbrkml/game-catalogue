package com.badrun.core.utils.mapper

import com.badrun.core.data.source.local.entity.ScreenshotEntity
import com.badrun.core.data.source.remote.response.ScreenshotResponse
import com.badrun.core.domain.model.Screenshot

object ScreenshotMapper {
    fun mapResponsesToEntities(input: List<ScreenshotResponse>, gameId: Int): List<ScreenshotEntity> {
        val screenshotList = ArrayList<ScreenshotEntity>()
        input.map {
            val screenshot = ScreenshotEntity(
                id = it.id,
                gameId = gameId,
                image = it.image,
                width = it.width,
                height = it.height
            )
            screenshotList.add(screenshot)
        }
        return screenshotList
    }

    fun mapEntitiesToDomain(input: List<ScreenshotEntity>): List<Screenshot> =
        input.map {
            Screenshot(
                id = it.id,
                gameId = it.gameId,
                image = it.image,
                width = it.width,
                height = it.height
            )
        }

}