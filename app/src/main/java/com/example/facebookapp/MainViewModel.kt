package com.example.facebookapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _name = MutableLiveData("")
    val name :LiveData<String> = _name

    private val _visibility = MutableLiveData(true)
    val visibility: LiveData<Boolean> = _visibility

    private val _scale = MutableLiveData(false)
    val scale: LiveData<Boolean> = _scale


    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onChangeVisibility() {
        _visibility.value = _visibility.value?.not()
    }

    fun onChangeScale() {
        _scale.value?.let {
            _scale.value = it.not()
        }?.run {
            _scale.value = true
        }
    }

    fun changeZoom(): Boolean {
        zoomChanged()
        return true
    }

    fun zoomChanged() {

    }

}