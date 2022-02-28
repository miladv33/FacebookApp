package com.example.facebookapp.queue

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import org.junit.Test

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp), contentAlignment = Alignment.TopCenter
    )
    {
        CurrentCircle("", mainViewModel)
    }
}

@Composable
fun CurrentCircle(testTag: String = "current", mainViewModel: MainViewModel) {
    val selectedList = arrayListOf<State<Boolean>>()
    mainViewModel.scaleList.forEach {
        selectedList.add(it.observeAsState(initial = false))
    }
    val scaleList = arrayListOf<State<Float>>()
    selectedList.forEach {
        val scale = animateFloatAsState(if (it.value) 2f else 1f)
        scaleList.add(scale)
    }

    Column {
        Row() {
            repeat(3) {
                Spacer(modifier = Modifier.size(40.dp))
                Box(
                    modifier = Modifier
                        .scale(scaleList[it].value)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                        .testTag(testTag)
                        .clickable { }
                )
            }
        }
        Spacer(modifier = Modifier.size(40.dp))
        Row {
            Button(onClick = { mainViewModel.goToPreviewsStep() }) {
                Text(color = Color.White, text = "back to last")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = { mainViewModel.goToNextStep() }) {
                Text(color = Color.White, text = "Go to next")
            }
        }
    }

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