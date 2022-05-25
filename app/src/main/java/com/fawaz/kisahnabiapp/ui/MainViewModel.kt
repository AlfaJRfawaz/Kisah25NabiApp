package com.fawaz.kisahnabiapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fawaz.kisahnabiapp.data.KisahNabiResponse
import com.fawaz.kisahnabiapp.data.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val kisahResponse = MutableLiveData<List<KisahNabiResponse>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    private fun getKisahNabi(responHandler: (List<KisahNabiResponse>) -> Unit, errorHandler: (Throwable) -> Unit) {
        ApiClient.getApiService().getKisahNabi()
            // membuat/menyediakan background thread / proses asynchronous
            .subscribeOn(Schedulers.io())
            // menentukan dimana thread akan dibuat
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getData() {
        // pakai lambda expression
        isLoading.value = true
        getKisahNabi({
            isLoading.value = false
            kisahResponse.value = it
        }, {
            isLoading.value = true
            isError.value = it
        })
    }
}