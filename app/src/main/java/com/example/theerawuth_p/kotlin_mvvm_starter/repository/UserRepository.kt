package com.example.theerawuth_p.kotlin_mvvm_starter.repository

import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.api.UserApi
import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.data.User
import com.example.theerawuth_p.kotlin_mvvm_starter.repository.db.UserDao
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */
class UserRepository(val userApi: UserApi, val userDao: UserDao) {

    var cachedUsers = emptyList<User>()

    fun getUsers(): Observable<List<User>> {
        return Observable.concatArrayDelayError(
                getUsersFromCache(), getUsersFromDb(), getUsersFromApi())
    }

    fun getUsersFromCache(): Observable<List<User>> {
        return Observable.just(cachedUsers).filter { it.isNotEmpty() }
                .doOnNext { Timber.d("Dispatching ${it.size} users from Cache.") }
    }

    fun getUsersFromDb(): Observable<List<User>> {
        return userDao.getUsers().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} users from DB.")
                }
    }

    fun getUsersFromApi(): Observable<List<User>> {
        return userApi.getUsers()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} users from API.")
                    cacheUsers(it)
                }
    }

    fun cacheUsers(users: List<User>) {
        Timber.d("Cached ${users.size} users in memory.")
        cachedUsers = users
        Observable.fromCallable { userDao.insertAll(users) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${users.size} users from API in DB.")
                }
    }


}