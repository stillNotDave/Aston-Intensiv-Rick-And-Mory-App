package ru.sfedu.rickandmortyapi2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sfedu.rickandmortyapi2.api.ApiAccess
import ru.sfedu.rickandmortyapi2.api.ResultEpisodeApi
import ru.sfedu.rickandmortyapi2.interfaces.getObjectInfo
import ru.sfedu.rickandmortyapi2.model.api.episode.Episode
import ru.sfedu.rickandmortyapi2.model.api.character.Character

class EpisodeViewModel : ViewModel(), getObjectInfo {
    val resultEpisodeApi = MutableLiveData<ResultEpisodeApi>()
    val errorMessageResultEpisode = MutableLiveData<String>()

    private val infoViewModel = InfoViewModel()
    private var lastPageReceived = 0

    fun getInfo() {
        val request = ApiAccess.retrofitService.getInfoEpisode()
        infoViewModel.requestInfo(request, this)
    }

    override fun getPage(page: Int) {
        if (page == lastPageReceived) {
            getInfo()
        } else {
            lastPageReceived = page
            getEpisode(lastPageReceived)
        }
    }

    fun getEpisode(page: Int) {
        val serviceResult = ApiAccess.retrofitService.getEpisode(page)
        serviceResult.enqueue(object : Callback<ResultEpisodeApi> {
            override fun onResponse(
                call: Call<ResultEpisodeApi>,
                response: Response<ResultEpisodeApi>
            ) {
                if (response.isSuccessful) {
                    resultEpisodeApi.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResultEpisodeApi>, t: Throwable) {
                errorMessageResultEpisode.postValue(t.message)
            }

        })
    }
}

class GetEpisodeViewModel() : ViewModel() {

    val episodeInfo = MutableLiveData<Episode>()
    val characterList = MutableLiveData<MutableList<Character>>()
    val errorMessageGetCharacterItem = MutableLiveData<String>()

    private var list: MutableList<Character> = mutableListOf()

    fun setEpisode(episode: Episode) {
        characterList.value?.clear()
        episodeInfo.value = episode
        val list: MutableList<Character> = mutableListOf()
        episode.episodeCharacters.forEach {
            getCharacterItem(it, list)
            this.list = list
        }
    }

    fun getCharacters() {
        characterList.postValue(list)
    }

    fun getCharacterItem(character: String, list: MutableList<Character>) {
        val serviceResult = ApiAccess.retrofitService.getCharacterUrl(character)
        serviceResult.enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful) {
                    val characterResponse = response.body() ?: Character()
                    list.add(characterResponse)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                errorMessageGetCharacterItem.postValue(t.message)
            }

        })
    }
}