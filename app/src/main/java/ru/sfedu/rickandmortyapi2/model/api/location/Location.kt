package ru.sfedu.rickandmortyapi2.model.api.location

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("id")
    val locationId: Int,

    @SerializedName("name")
    val locationName: String,

    @SerializedName("type")
    val locationType: String,

    @SerializedName("dimension")
    val locationDimension: String,

    @SerializedName("residents")
    val locationResidents: ArrayList<String>,

    @SerializedName("url")
    val locationUrl: String,

    @SerializedName("created")
    val locationCreated: String,
) {
    constructor() : this(0, "", "", "", arrayListOf(), "", "")
}