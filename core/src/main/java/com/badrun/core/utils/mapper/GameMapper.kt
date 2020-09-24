package com.badrun.core.utils.mapper

import com.badrun.core.data.source.local.entity.GameEntity
import com.badrun.core.data.source.remote.response.GameResponse
import com.badrun.core.domain.model.Game

object GameMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val tourismList = ArrayList<GameEntity>()
        input.map {
            val tourism = GameEntity(
                gameId = it.id,
                description = it.description ?: "",
                name = it.name,
                rating = it.rating ?: 0.0,
                dateReleased = it.dateReleased ?: "1900-00-00",
                backgroundImage = it.backgroundImage ?: "",
                reviewsCount = it.reviewsCount ?: 0,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapResponsesToEntity(input: GameResponse): GameEntity {
        return GameEntity(
            gameId = input.id,
            description = input.description ?: "",
            name = input.name,
            rating = input.rating ?: 0.0,
            dateReleased = input.dateReleased ?: "1900-00-00",
            backgroundImage = input.backgroundImage ?: "",
            reviewsCount = input.reviewsCount ?: 0,
            isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameId = it.gameId,
                description = it.description,
                name = it.name,
                rating = it.rating,
                dateReleased = it.dateReleased,
                backgroundImage = it.backgroundImage,
                isFavorite = it.isFavorite,
                reviewsCount = it.reviewsCount
            )
        }

    fun mapEntityToDomain(input: GameEntity): Game =
        Game(
            gameId = input.gameId,
            description = input.description,
            name = input.name,
            rating = input.rating,
            dateReleased = input.dateReleased,
            backgroundImage = input.backgroundImage,
            isFavorite = input.isFavorite,
            reviewsCount = input.reviewsCount
        )

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.gameId,
        description = input.description,
        name = input.name,
        rating = input.rating,
        dateReleased = input.dateReleased,
        backgroundImage = input.backgroundImage,
        isFavorite = input.isFavorite,
        reviewsCount = input.reviewsCount
    )
}