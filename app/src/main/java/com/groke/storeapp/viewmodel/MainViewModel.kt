package com.groke.storeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveData: MutableLiveData<ApplicationState> = MutableLiveData()) :
    ViewModel() {
    fun getLiveData(): LiveData<ApplicationState> {
        return liveData
    }

    fun emulateRequest() {
        Thread {
            liveData.postValue((ApplicationState.Loading("Loading")))
            sleep(5000)
            liveData.postValue((ApplicationState.MainScreen("MainScreen")))
        }.start()
    }
}