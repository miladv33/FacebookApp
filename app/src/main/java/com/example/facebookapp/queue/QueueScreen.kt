package com.example.facebookapp.queue

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.facebookapp.MainViewModel
import com.example.facebookapp.R
import com.example.facebookapp.ui.theme.nextStepCircle
import com.example.facebookapp.ui.theme.selectedCircle
import com.example.facebookapp.ui.theme.unselectedCircle


var visible = MutableLiveData<Boolean>()

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TestAnimation(mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            ,contentAlignment = Alignment.Center
    )
    {
        Column {
            Box(contentAlignment = Alignment.CenterStart) {
                LinesProgress(mainViewModel = mainViewModel)
                CurrentCircle("", mainViewModel)
            }
            StepButton(mainViewModel = mainViewModel)
        }
    }
}

@Composable
fun LinesProgress(mainViewModel: MainViewModel) {
    FixedLine()
    Row {
        repeat(2) {
            ButtonAnimation(it, mainViewModel = mainViewModel)
        }
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


    Row() {
        repeat(3) {
            val step = mainViewModel.queueStep.observeAsState()
            val color = animateColorAsState(
                when {
                    step.value == it -> selectedCircle
                    step.value!! < it -> nextStepCircle
                    else -> unselectedCircle
                }
            )
            val iconColor = animateColorAsState(
                when {
                    step.value!! >= it -> Color.White
                    else -> Color.Gray
                }
            )
            Column {
                Box(
                    modifier = Modifier
                        .scale(scaleList[it].value)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color.value)
                        .testTag(testTag)
                        .clickable { }, contentAlignment = Alignment.Center
                ) {
                    val image: Painter = painterResource(id = mainViewModel.iconList[it])
                    Image(painter = image, contentDescription = "", colorFilter = ColorFilter.tint(iconColor.value))
                }
            }
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}

@Composable
fun StepButton(mainViewModel: MainViewModel) {
    Spacer(modifier = Modifier.size(80.dp))
    Row {
        Button(onClick = { mainViewModel.goToPreviewsStep() }) {
            Text(color = Color.White, text = "back")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = { mainViewModel.goToNextStep() }) {
            Text(color = Color.White, text = "next")
        }
    }
}

@Composable
fun ButtonAnimation(index: Int, mainViewModel: MainViewModel) {
    val isStepped = mainViewModel.lineProgressListState[index].observeAsState()
    val size = if (isStepped.value == false) 0.dp else 100.dp
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(backgroundColor = unselectedCircle),
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
            .height(10.dp)
            .width(size)
    ) {}
}

@Composable
fun FixedLine() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(backgroundColor = nextStepCircle),
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
            .height(10.dp)
            .width(200.dp)
    ) {}
}