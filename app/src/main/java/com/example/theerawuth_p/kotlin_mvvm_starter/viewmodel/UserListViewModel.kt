package com.example.theerawuth_p.kotlin_mvvm_starter.viewmodel

import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.UserDataModel
import com.example.theerawuth_p.kotlin_mvvm_starter.viewmodel.data.UsersList
import io.reactivex.Observable

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */
class UserListViewModel(val userDataModel: UserDataModel) {

    fun getUsers(): Observable<UsersList> {
        // Create the data for your UI, the users lists and maybe some additional data need as well
        return userDataModel.getUsers().map { UsersList(it, "Users loaded successfully!") }
    }
}