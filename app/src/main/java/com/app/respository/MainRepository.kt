package com.app.respository

import androidx.lifecycle.LiveData
import com.app.bases.BaseRepository
import com.app.database.AppDao
import com.app.database.CityDBModel
import com.app.network.ApiClientInterface
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Muzzamil Saleem
 */
@Singleton
class MainRepository @Inject constructor(
    private val apiClientInterface: ApiClientInterface,
) :
    BaseRepository() {

    suspend fun callLogin(
        userName: String,
        password: String
    ) = safeApiCall {
        apiClientInterface.callLogin(userName, password)
    }


}