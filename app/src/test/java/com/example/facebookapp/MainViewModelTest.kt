package com.example.facebookapp

import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import org.mockito.Spy
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

    @Spy
    lateinit var mainViewModel: MainViewModel

    @Before
    fun init() {

    }

    @Test
    fun test() {
        mainViewModel = spy(MainViewModel::class.java)
        mainViewModel.onChangeScale()
        assertEquals(mainViewModel.scale.value, true)
    }


}