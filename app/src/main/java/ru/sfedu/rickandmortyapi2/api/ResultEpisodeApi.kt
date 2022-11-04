package ru.sfedu.rickandmortyapi2.api

import com.google.gson.annotations.SerializedName
import ru.sfedu.rickandmortyapi2.model.api.info.Info
import ru.sfedu.rickandmortyapi2.model.api.episode.Episode

data class ResultEpisodeApi(
    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val episode: List<Episode>,
) {
    constructor() : this(Info(), listOf())
}
