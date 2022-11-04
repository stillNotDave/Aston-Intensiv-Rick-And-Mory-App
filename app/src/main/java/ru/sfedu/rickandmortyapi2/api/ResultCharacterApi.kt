package ru.sfedu.rickandmortyapi2.api

import com.google.gson.annotations.SerializedName
import ru.sfedu.rickandmortyapi2.model.api.info.Info
import ru.sfedu.rickandmortyapi2.model.api.character.Character

data class ResultCharacterApi(
    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val characterList: List<Character>
) {
    constructor() : this(Info(), listOf())
}
