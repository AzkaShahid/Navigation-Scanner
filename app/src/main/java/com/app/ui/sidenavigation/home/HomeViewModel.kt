package com.app.ui.sidenavigation.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.bases.BaseViewModel
import com.app.database.CityDBModel
import com.app.models.prayer.PrayerResponse
import com.app.network.Resource
import com.app.respository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val repository: MainRepository
) : BaseViewModel() {


}
