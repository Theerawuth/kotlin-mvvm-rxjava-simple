package com.example.theerawuth_p.kotlin_mvvm_starter

import android.app.Application
import android.arch.persistence.room.Room
import com.example.theerawuth_p.kotlin_mvvm_starter.repository.UserRepository
import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.api.UserApi
import com.example.theerawuth_p.kotlin_mvvm_starter.repository.db.AppDatabase
import com.example.theerawuth_p.kotlin_mvvm_starter.viewmodel.UserListViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Created by theerawuth_p on 10/16/2017 AD.
 */
class MainApplication : Application() {

    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var userApi: UserApi
        private lateinit var userRepository: UserRepository
        private lateinit var userListViewModel: UserListViewModel
        private lateinit var appDatabase: AppDatabase

        fun injectUserApi() = userApi

        fun injectUserListViewModel() = userListViewModel

        fun injectUserDao() = appDatabase.userDao()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://randomapi.com/api/")
                .build()

        userApi = retrofit.create(UserApi::class.java)
        appDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "mvvm-database").build()
        userRepository = UserRepository(userApi, appDatabase.userDao())
        userListViewModel = UserListViewModel(userRepository)

    }
}