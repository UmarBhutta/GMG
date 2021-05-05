package com.gmg.user.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmg.user.data.model.User
import com.gmg.user.data.repo.UserRepo
import com.gmg.user.utils.LiveDataState
import com.gmg.user.utils.NetworkUtils
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepo: UserRepo,
    private val networkHelper: NetworkUtils
) : ViewModel() {
    private var page = 1
    private val _users = MutableLiveData<LiveDataState<List<User>>>()
    val users: LiveData<LiveDataState<List<User>>>
        get() = _users

    init {
        fetchUsers(page)
    }

    fun fetchUsers(pageNumber:Int) {
        viewModelScope.launch {

            if(pageNumber == 1){
                _users.postValue(LiveDataState.loading(null))
            }else _users.postValue(LiveDataState.loading(users.value!!.data))

            //if (networkHelper.isNetworkConnected()) {
                userRepo.getUsers(pageNumber).let {
                    if (it.isSuccessful){
                        if (it.body() != null){
                            _users.postValue(LiveDataState.completed(it.body()!!.results))
                        }else _users.postValue(LiveDataState.emptyData(null))
                    }else _users.postValue(LiveDataState.error(it.errorBody().toString(), null))
                }

            //} else _users.postValue(LiveDataState.error("No internet connection", null))
        }
    }

    fun loadMore(){
        page += 1
        fetchUsers(page)
    }

    fun loadFirstPage(){
        fetchUsers(1)
    }
}