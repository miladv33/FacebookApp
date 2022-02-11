package com.example.facebookapp.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.facebookapp.component.FaceBookLogo
import com.example.facebookapp.component.LogoButton

@Preview
@Composable
fun Header(){
    Row {
       FaceBookLogo()
        LogoButton()


        LogoButton()



        LogoButton()
        LogoButton()
    }
}