package com.example.theerawuth_p.kotlin_mvvm_starter.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.data.User
import io.reactivex.Single

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)
}