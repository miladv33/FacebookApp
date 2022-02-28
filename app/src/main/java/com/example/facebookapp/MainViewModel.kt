package com.example.facebookapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val scaleList: ArrayList<MutableLiveData<Boolean>> = ArrayList()

    private val _queueStep = MutableLiveData(0)
    val queueStep: LiveData<Int> = _queueStep

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _visibility = MutableLiveData(true)
    val visibility: LiveData<Boolean> = _visibility

    private val _scale = MutableLiveData(false)
    val scale: LiveData<Boolean> = _scale

    init {
        setObservableLiveDataScale()
    }

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onChangeVisibility() {
        _visibility.value = _visibility.value?.not()
    }


    fun goToNextStep() {
        changeStepToForward()
        onChangeScale()
    }

    fun changeStepToForward() {
        if (_queueStep.value?.plus(1)!! < scaleList.size)
            _queueStep.value = _queueStep.value?.plus(1)
    }

    fun onChangeScale() {
        scaleList.forEachIndexed { index, element ->
            element.value = index == _queueStep.value
        }
    }

    private fun setObservableLiveDataScale() {
        scaleList.add(MutableLiveData(true))
        scaleList.add(MutableLiveData(false))
        scaleList.add(MutableLiveData(false))
    }

    fun goToPreviewsStep() {
        changeStepToPreviews()
        onChangeScale()
    }

    fun changeStepToPreviews() {
        if (_queueStep.value?.minus(1)!! > -1)
            _queueStep.value = _queueStep.value?.minus(1)
    }

}