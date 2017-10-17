package com.example.theerawuth_p.kotlin_mvvm_starter.repository

import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.api.UserApi
import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.data.User
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */
class UserRepository(val userApi: UserApi) {

    var cachedUsers = emptyList<User>()

    fun getUsers(): Observable<List<User>> {
        if(cachedUsers.isEmpty()) {
            Timber.d("Returning users from API")
            return userApi.getUsers()
                    .doOnNext { cachedUsers = it}
        } else {
            Timber.d("Returning cached users")
            return Observable.just(cachedUsers)
                    .mergeWith { userApi.getUsers() }
                    .doOnNext { cachedUsers = it }
        }
    }
}