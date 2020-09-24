package com.badrun.core.utils.mapper

import com.badrun.core.data.source.local.entity.StoreEntity
import com.badrun.core.data.source.remote.response.StoreResponse
import com.badrun.core.domain.model.Store

object StoreMapper {
    fun mapResponsesToEntities(input: List<StoreResponse>): List<StoreEntity> {
        val screenshotList = ArrayList<StoreEntity>()
        input.map {
            val screenshot = StoreEntity(
                id = it.id,
                gameId = it.gameId,
                storeId = it.storeId,
                url = it.url
            )
            screenshotList.add(screenshot)
        }
        return screenshotList
    }

    fun mapEntitiesToDomain(input: List<StoreEntity>): List<Store> =
        input.map {
            Store(
                id = it.id,
                gameId = it.gameId,
                storeId = it.storeId,
                url = it.url
            )
        }

}