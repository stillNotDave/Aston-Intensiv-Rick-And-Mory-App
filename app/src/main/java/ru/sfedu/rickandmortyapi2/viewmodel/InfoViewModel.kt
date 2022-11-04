package ru.sfedu.rickandmortyapi2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sfedu.rickandmortyapi2.api.ResultInfoApi
import ru.sfedu.rickandmortyapi2.interfaces.getObjectInfo

class InfoViewModel : ViewModel() {
    private var resultInfo = ResultInfoApi()
    var charactersPage = 0
    val errorInfoMessage = MutableLiveData<String>()


    fun requestInfo(request: Call<ResultInfoApi>, getObjectInfo: getObjectInfo) {
        request.enqueue(object : Callback<ResultInfoApi> {
            override fun onResponse(
                call: Call<ResultInfoApi>,
                response: Response<ResultInfoApi>
            ) {
                if (response.isSuccessful) {
                    resultInfo = response.body() ?: ResultInfoApi()
                    charactersPage = (1..resultInfo.info.infoPages).random()
                    getObjectInfo.getPage(charactersPage)
                }
            }

            override fun onFailure(call: Call<ResultInfoApi>, t: Throwable) {
                errorInfoMessage.postValue(t.message)
            }
        })
    }
}