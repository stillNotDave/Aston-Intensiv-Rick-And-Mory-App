package ru.sfedu.rickandmortyapi2.model.api.character

import com.google.gson.annotations.SerializedName

data class CharacterLocation(
    @SerializedName("name")
    val locationName: String,

    @SerializedName("url")
    val locationUrl: String
) {
    constructor() : this("", "")
}
