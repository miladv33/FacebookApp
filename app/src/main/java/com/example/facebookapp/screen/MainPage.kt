package com.example.facebookapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebookapp.R
import com.example.facebookapp.component.CircleLogo
import com.example.facebookapp.component.CircularImage
import com.example.facebookapp.component.LogoButton
import com.example.facebookapp.component.LogoGrayButton
import com.example.facebookapp.ui.theme.LocalLogoImageId
import com.example.facebookapp.ui.theme.LocalLogoImageSize

@Preview
@Composable
fun Header() {
    Row(Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleLogo()
        }
    }
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Row {
            Spacer(Modifier.weight(0.5f))
            LogoGrayButton()
            Spacer(Modifier.weight(0.03f))
            LogoGrayButton(R.drawable.ic_notification)
            Spacer(Modifier.weight(0.03f))
            LogoGrayButton(R.drawable.ic_messenger)
            Spacer(Modifier.weight(0.03f))

            CompositionLocalProvider(
                LocalLogoImageId provides R.drawable.user_avatar,
                LocalLogoImageSize provides dimensionResource(id = R.dimen.userAvatarSize)
            ) {
                CircularImage()
            }
        }
    }
}