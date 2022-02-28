package com.example.facebookapp

import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@HiltAndroidTest
@Config(sdk = [30])
@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    //    @get:Rule
//    var hiltRule = HiltAndroidRule(this)
//
//    @Inject


    @Before
    fun init() {
    }

    @Test
    fun change_position_to_next_circle() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.goToNextStep()
        verify(mainViewModel).changeStepToForward()
        assertEquals(mainViewModel.queueStep.value, 1)
    }

    @Test
    fun firstItemIsScaled() {
        val mainViewModel = spy(MainViewModel::class.java)
        for (scale in mainViewModel.scaleList) {
            if (scale == mainViewModel.scaleList.first())
                assertEquals(scale.value, true)
            else
                assertEquals(scale.value, false)
        }
    }

    @Test
    fun change_Current_Scale_and_move_to_another_circle() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.goToNextStep()
        for (scale in mainViewModel.scaleList) {
            if (scale == mainViewModel.scaleList[1])
                assertEquals(scale.value, true)
            else
                assertEquals(scale.value, false)
        }
    }

    @Test
    fun connote_scale_out_the_last_circle_() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.scaleList
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        assertEquals(mainViewModel.queueStep.value, mainViewModel.scaleList.size - 1)
    }

    @Test
    fun connote_scale_out_the_first_circle_if_never_went_to_next_step() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.goToPreviewsStep()
        verify(mainViewModel).changeStepToPreviews()
        assertEquals(mainViewModel.queueStep.value, 0)
    }

    @Test
    fun change_position_to_lastStep() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.goToNextStep()
        mainViewModel.goToPreviewsStep()
        verify(mainViewModel).changeStepToPreviews()
        assertEquals(mainViewModel.queueStep.value, 0)
    }
}