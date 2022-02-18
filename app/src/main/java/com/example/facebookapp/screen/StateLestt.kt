package com.example.facebookapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.facebookapp.MainViewModel
import com.example.facebookapp.component.CircleLogo


@Composable
fun FirstScreen(mainViewModel: MainViewModel) {
    val name: String by mainViewModel.name.observeAsState("")
    ScreenContent(name = name, onNameChange = {
        mainViewModel.onNameChange(it)
    })
}

@Composable
fun ScreenContent(
    name: String,
    onNameChange: (String) -> Unit
) {
    Column {
        TextField(value = name, onValueChange = onNameChange, label = { CircleLogo() })
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = name)
    }
}