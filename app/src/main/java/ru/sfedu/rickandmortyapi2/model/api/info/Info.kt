package ru.sfedu.rickandmortyapi2.model.api.info

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    val infoCount: Int,

    @SerializedName("pages")
    val infoPages: Int,

    @SerializedName("next")
    val infoNext: String?,

    @SerializedName("prev")
    val infoPreviously: String?,
) {
    constructor() : this(0, 0, null, null)
}
