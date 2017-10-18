package com.example.theerawuth_p.kotlin_mvvm_starter.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.data.User

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}