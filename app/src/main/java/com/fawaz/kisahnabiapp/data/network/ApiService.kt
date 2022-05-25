package com.fawaz.kisahnabiapp.data.network

import com.fawaz.kisahnabiapp.data.KisahNabiResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("kisahnabi")
    fun getKisahNabi() : Flowable<List<KisahNabiResponse>>
}