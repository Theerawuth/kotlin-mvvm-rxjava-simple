package com.example.theerawuth_p.kotlin_mvvm_starter

import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.api.UserApi
import com.example.theerawuth_p.kotlin_mvvm_starter.datamodel.data.User
import com.example.theerawuth_p.kotlin_mvvm_starter.repository.UserRepository
import com.example.theerawuth_p.kotlin_mvvm_starter.repository.db.UserDao
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import org.mockito.Mockito.`when`

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class UserRepositoryTest {

    lateinit var userRepository: UserRepository
    lateinit var userApi: UserApi
    lateinit var userDao: UserDao

    @Before
    fun setup() {
        userApi = mock()
        userDao = mock()
        `when`(userDao.getUsers()).thenReturn(Single.just(emptyList()))
        userRepository = UserRepository(userApi, userDao)
    }

    @Test
    fun test_emptyCache_noDataOnApi_returnsEmptyList() {
        `when`(userApi.getUsers()).thenReturn(Observable.just(emptyList<User>()))

        userRepository.getUsers().test()
                .assertValue { it.isEmpty() }
    }

    @Test
    fun test_emptyCache_hasDataOnApi_returnApiData() {
        `when`(userApi.getUsers()).thenReturn(Observable.just(listOf(aRandomUser())))

        userRepository.getUsers().test()
                .assertValueCount(1)
                .assertValue { it.size == 1 }
    }

    @Test
    fun test_hasCacheData_hasApiData_returnBothData() {
        val cachedData = listOf(aRandomUser())
        val apiData = listOf(aRandomUser(), aRandomUser())

        `when`(userApi.getUsers()).thenReturn(Observable.just(apiData))
        userRepository.cachedUsers = cachedData

        userRepository.getUsers().test()
                //Both cached & API data delivered
                .assertValueCount(2)
                //First cache data delivered
                .assertValueAt(0, { it == cachedData})
                //Secondly api data delivered
                .assertValueAt(1, { it == apiData})
    }

    @Test
    fun test_cache_updatedWithApiData() {
        val apiData = listOf(aRandomUser(), aRandomUser())
        `when`(userApi.getUsers()).thenReturn(Observable.just(apiData))

        userRepository.getUsers().test()
        assertEquals(userRepository.cachedUsers, apiData)
    }



    fun aRandomUser() = User("mail@test.com", "John", UUID.randomUUID().toString().take(5))
}