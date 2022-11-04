package ru.sfedu.rickandmortyapi2.api

import com.google.gson.annotations.SerializedName
import ru.sfedu.rickandmortyapi2.model.api.info.Info
import ru.sfedu.rickandmortyapi2.model.api.location.Location

data class ResultLocationApi(
    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val locationList: List<Location>,
) {
    constructor() : this(Info(), listOf())
}