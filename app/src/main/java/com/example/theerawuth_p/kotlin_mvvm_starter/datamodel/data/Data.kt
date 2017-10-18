package com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */
@Entity(tableName = "users")

data class User constructor(
        @PrimaryKey
        @ColumnInfo(name = "email")
        val email: String,
        @ColumnInfo(name = "firstName")
        val first: String,
        @ColumnInfo(name = "lastName")
        val last: String
)