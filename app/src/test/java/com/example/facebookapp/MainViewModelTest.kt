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

    lateinit var mainViewModel:MainViewModel

    @Before
    fun init() {
        mainViewModel = spy(MainViewModel::class.java)
    }

    @Test
    fun `change position to next circle`() {
        mainViewModel.goToNextStep()
        verify(mainViewModel).changeStepToForward()
        assertEquals(mainViewModel.queueStep.value, 1)
    }

    @Test
    fun `first Item Is Scaled`() {
        for (scale in mainViewModel.scaleList) {
            if (scale == mainViewModel.scaleList.first())
                assertEquals(scale.value, true)
            else
                assertEquals(scale.value, false)
        }
    }

    @Test
    fun `change Current Scale and move to another circle`() {
        mainViewModel.goToNextStep()
        for (scale in mainViewModel.scaleList) {
            if (scale == mainViewModel.scaleList[1])
                assertEquals(scale.value, true)
            else
                assertEquals(scale.value, false)
        }
    }

    @Test
    fun `connote scale out the last circle`() {
        mainViewModel.scaleList
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        assertEquals(mainViewModel.queueStep.value, mainViewModel.scaleList.size - 1)
    }

    @Test
    fun `connote scale out the first circle if it never went to the next step`() {
        mainViewModel.goToPreviewsStep()
        verify(mainViewModel).changeStepToPreviews()
        assertEquals(mainViewModel.queueStep.value, 0)
    }

    @Test
    fun `change position to previous step`() {
        mainViewModel.goToNextStep()
        mainViewModel.goToPreviewsStep()
        verify(mainViewModel).changeStepToPreviews()
        assertEquals(mainViewModel.queueStep.value, 0)
    }


    @Test
    fun `progressLine to next step`() {
        mainViewModel.goToNextStep()
        verify(mainViewModel).changeLineSizeToStepForward()
        assertEquals(mainViewModel.lineProgressListState[0].value, true)
        assertEquals(mainViewModel.lineProgressListState[1].value, false)
        mainViewModel.goToNextStep()
        assertEquals(mainViewModel.lineProgressListState[0].value, true)
        assertEquals(mainViewModel.lineProgressListState[1].value, true)
    }

    @Test
    fun `progressLine To Previous step`() {
        mainViewModel.goToNextStep()
        mainViewModel.goToPreviewsStep()
        verify(mainViewModel).changeLineSizeToStepBack()
        assertEquals(mainViewModel.lineProgressListState[0].value, false)
    }

}