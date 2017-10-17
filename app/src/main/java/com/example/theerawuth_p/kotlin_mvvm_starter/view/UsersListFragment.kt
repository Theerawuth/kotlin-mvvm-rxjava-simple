package com.example.theerawuth_p.kotlin_mvvm_starter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.theerawuth_p.kotlin_mvvm_starter.MainApplication
import com.example.theerawuth_p.kotlin_mvvm_starter.R
import com.example.theerawuth_p.kotlin_mvvm_starter.viewmodel.data.UsersList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user_list.*

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */
class UsersListFragment : MvvmFragment() {

    val userListViewModel = MainApplication.injectUserListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        subscribe(userListViewModel.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showUsers(it)
                }, {
                    showError()
                }))
    }

    fun showUsers(data: UsersList) {
        usersList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, data.users)
    }

    fun showError() {
        Toast.makeText(context, "An error occurred :(", Toast.LENGTH_SHORT).show()
    }
}