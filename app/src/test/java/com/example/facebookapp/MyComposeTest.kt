package com.example.facebookapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

// file: app/src/androidTest/java/com/package/MyComposeTest.kt

class MyComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun MyTest() {
        // Start the app
//        composeTestRule.setContent {
//            MyAppTheme {
//                MainScreen(uiState = fakeUiState, /*...*/)
//            }
//        }
//
//
//        composeTestRule.onNodeWithText("Continue").performClick()
//
//        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}