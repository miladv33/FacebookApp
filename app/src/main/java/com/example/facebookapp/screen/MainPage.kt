package com.example.facebookapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebookapp.component.FaceBookLogo
import com.example.facebookapp.component.LogoButton
import com.example.facebookapp.component.LogoGrayButton

@Preview
@Composable
fun Header(){
    Row(Modifier.fillMaxWidth()) {
        Column(
           verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FaceBookLogo()
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LogoGrayButton()
            LogoGrayButton()
            LogoGrayButton()
            LogoGrayButton()
            FaceBookLogo()
        }

    }
}