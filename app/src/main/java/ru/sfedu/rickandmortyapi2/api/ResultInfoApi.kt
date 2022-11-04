package ru.sfedu.rickandmortyapi2.api

import com.google.gson.annotations.SerializedName
import ru.sfedu.rickandmortyapi2.model.api.info.Info

data class ResultInfoApi(
    @SerializedName("info")
    val info: Info
) {
    constructor() : this(Info())
}
