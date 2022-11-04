package ru.sfedu.rickandmortyapi2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sfedu.rickandmortyapi2.api.ApiAccess
import ru.sfedu.rickandmortyapi2.api.ResultCharacterApi
import ru.sfedu.rickandmortyapi2.interfaces.getObjectInfo
import ru.sfedu.rickandmortyapi2.model.api.episode.Episode
import ru.sfedu.rickandmortyapi2.model.api.character.Character

class CharacterViewModel : ViewModel(), getObjectInfo {

    val resultCharacterApi = MutableLiveData<ResultCharacterApi>()
    val errorMessageResultCharacter = MutableLiveData<String>()

    private val infoViewModel = InfoViewModel()
    private var lastPageReceived = 0

    fun getInfo() {
        val request = ApiAccess.retrofitService.getInfoCharacter()
        infoViewModel.requestInfo(request, this)
    }

    override fun getPage(page: Int) {
        if (page == lastPageReceived) {
            getInfo()
        } else {
            lastPageReceived = page
            getCharacter(lastPageReceived)
        }
    }

    fun getCharacter(page: Int) {
        val serviceResult = ApiAccess.retrofitService.getCharacter(page)
        serviceResult.enqueue(object : Callback<ResultCharacterApi> {
            override fun onResponse(
                call: Call<ResultCharacterApi>,
                response: Response<ResultCharacterApi>
            ) {
                if (response.isSuccessful) {
                    resultCharacterApi.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResultCharacterApi>, t: Throwable) {
                Log.e("Failure", t.message.toString())
                errorMessageResultCharacter.postValue(t.message)
            }

        })
    }
}

class GetCharacterViewModel : ViewModel() {

    val characterInfo = MutableLiveData<Character>()
    val episodeList = MutableLiveData<MutableList<Episode>>()
    val errorMessageGetEpisodeItem = MutableLiveData<String>()

    private var list: MutableList<Episode> = mutableListOf()

    fun setCharacter(character: Character) {
        episodeList.value?.clear()
        characterInfo.value = character
        val list: MutableList<Episode> = mutableListOf()
        character.characterEpisode.forEach {
            getEpisodeItem(it, list)
            this.list = list
        }
    }

    fun getEpisodes() {
        episodeList.postValue(list)
    }

    fun getEpisodeItem(episode: String, list: MutableList<Episode>) {
        val serviceResult = ApiAccess.retrofitService.getEpisodeUrl(episode)
        serviceResult.enqueue(object : Callback<Episode> {
            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                if (response.isSuccessful) {
                    val episodeResponse = response.body() ?: Episode()
                    list.add(episodeResponse)
                }
            }

            override fun onFailure(call: Call<Episode>, t: Throwable) {
                errorMessageGetEpisodeItem.postValue(t.message)
            }

        })
    }
}