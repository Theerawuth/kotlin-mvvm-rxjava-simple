package com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.api

import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.data.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */
interface UserApi {

    @GET("6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
    fun getUsers(): Observable<List<User>>

}