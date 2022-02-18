package com.example.facebookapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _name = MutableLiveData("")
    val name :LiveData<String> = _name

    fun onNameChange(newName: String) {
        _name.value = newName
    }

}