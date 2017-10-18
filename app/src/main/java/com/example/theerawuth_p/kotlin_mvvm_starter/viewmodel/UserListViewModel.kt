package com.example.theerawuth_p.kotlin_mvvm_starter.viewmodel

import com.example.theerawuth_p.kotlin_mvvm_starter.repository.UserRepository
import com.example.theerawuth_p.kotlin_mvvm_starter.viewmodel.data.UsersList
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */
class UserListViewModel(val userRepository: UserRepository) {

    fun getUsers(): Observable<UsersList> {
        // Create the data for your UI, the users lists and maybe some additional data need as well
        return userRepository.getUsers()
                .map {
                    Timber.d("Mapping users to UIData...")
                    UsersList(it.take(10), "Top 10 Users")
                }
                .onErrorReturn {
                    UsersList(emptyList(), "An error occured", it)
                }
    }
}