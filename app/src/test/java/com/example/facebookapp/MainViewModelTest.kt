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
    fun `change position to next circle`() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.goToNextStep()
        verify(mainViewModel).changeStepToForward()
        assertEquals(mainViewModel.queueStep.value, 1)
    }

    @Test
    fun `first Item Is Scaled`() {
        val mainViewModel = spy(MainViewModel::class.java)
        for (scale in mainViewModel.scaleList) {
            if (scale == mainViewModel.scaleList.first())
                assertEquals(scale.value, true)
            else
                assertEquals(scale.value, false)
        }
    }

    @Test
    fun `change Current Scale and move to another circle`() {
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
    fun `connote scale out the last circle`() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.scaleList
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        mainViewModel.changeStepToForward()
        assertEquals(mainViewModel.queueStep.value, mainViewModel.scaleList.size - 1)
    }

    @Test
    fun `connote scale out the first circle if it never went to the next step`() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.goToPreviewsStep()
        verify(mainViewModel).changeStepToPreviews()
        assertEquals(mainViewModel.queueStep.value, 0)
    }

    @Test
    fun `change position to previous step`() {
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.goToNextStep()
        mainViewModel.goToPreviewsStep()
        verify(mainViewModel).changeStepToPreviews()
        assertEquals(mainViewModel.queueStep.value, 0)
    }


    @Test
    fun `progressLine to next step`() {
        val mainViewModel = spy(MainViewModel::class.java)
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
        val mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.goToNextStep()
        mainViewModel.goToPreviewsStep()
        verify(mainViewModel).changeLineSizeToStepBack()
        assertEquals(mainViewModel.lineProgressListState[0].value, false)
    }

}