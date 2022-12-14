package ru.sfedu.rickandmortyapi2.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import ru.sfedu.rickandmortyapi2.model.api.episode.Episode
import ru.sfedu.rickandmortyapi2.model.api.character.Character

const val BASE_URL = "https://rickandmortyapi.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("character")
    fun getInfoCharacter(): Call<ResultInfoApi>

    @GET("location")
    fun getInfoLocation(): Call<ResultInfoApi>

    @GET("episode")
    fun getInfoEpisode(): Call<ResultInfoApi>

    @GET("character?")
    fun getCharacter(@Query("page") page: Int): Call<ResultCharacterApi>

    @GET("location?")
    fun getLocation(@Query("page") page: Int): Call<ResultLocationApi>

    @GET("episode?")
    fun getEpisode(@Query("page") page: Int): Call<ResultEpisodeApi>

    @GET
    fun getEpisodeUrl(@Url url: String): Call<Episode>

    @GET
    fun getCharacterUrl(@Url url: String): Call<Character>

}

object ApiAccess {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}