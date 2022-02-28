package com.example.facebookapp.queue

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.facebookapp.MainViewModel

@Composable
fun QueueScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            JointButton()
        }
    }

}

var visible = MutableLiveData<Boolean>()

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TestAnimation(mainViewModel: MainViewModel) {

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp), contentAlignment = Alignment.TopCenter)
    {
        CurrentCircle("", mainViewModel)
    }
}

@Composable
fun CurrentCircle(testTag: String = "current", mainViewModel: MainViewModel) {
    val selected by mainViewModel.scale.observeAsState(false)
    val scale = animateFloatAsState(if (selected) 2f else 1f)
    Box(
        modifier = Modifier
            .scale(scale.value)
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Red)
            .testTag(testTag)
            .clickable { mainViewModel.onChangeScale() }
    )
}

@Composable
fun JointButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .testTag("joinButton")
            .size(50.dp)
    ) {
    }
}