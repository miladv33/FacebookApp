package com.example.facebookapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val lineProgressListState: ArrayList<MutableLiveData<Boolean>> = ArrayList()
    val scaleList: ArrayList<MutableLiveData<Boolean>> = ArrayList()

    private val _queueStep = MutableLiveData(0)
    val queueStep: LiveData<Int> = _queueStep

    init {
        setObservableLiveDataScale()
        setProgressList()
    }

    fun goToNextStep() {
        changeStepToForward()
        onChangeScale()
        changeLineSizeToStepForward()
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
        changeLineSizeToStepBack()
    }

    fun changeStepToPreviews() {
        if (_queueStep.value?.minus(1)!! > -1)
            _queueStep.value = _queueStep.value?.minus(1)
    }

    fun changeLineSizeToStepForward() {
        lineProgressListState.forEachIndexed { index, element ->
                if (index ==  _queueStep.value?.minus(1)){
                    element.value = true
                }
        }
    }

    fun setProgressList() {
        lineProgressListState.add(MutableLiveData(false))
        lineProgressListState.add(MutableLiveData(false))
    }

    fun changeLineSizeToStepBack() {
        lineProgressListState.forEachIndexed { index, element ->
            if (index == _queueStep.value) {
                element.value = false
            }
        }
    }

}