package com.example.facebookapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebookapp.R
import com.example.facebookapp.ui.theme.*
import com.example.facebookapp.ui.theme.model.StoryImageSize


@Preview
@Composable
fun LogoGrayButton(iconId:Int = R.drawable.ic_search) {

    Box(){
        CompositionLocalProvider(
            LocalLogoImageSize provides dimensionResource(id = R.dimen.userAvatarSize),
            LocalLogoImageId provides R.drawable.ic_ellipse
        ) {
            CircularImage()
        }
        Box(modifier = Modifier.size(dimensionResource(id = R.dimen.userAvatarSize)),
            contentAlignment = Alignment.Center) {
            CompositionLocalProvider(
                LocalLogoImageSize provides dimensionResource(id = R.dimen.userGrayAvatarSize),
                LocalLogoImageId provides iconId
            ) {
                CircularImage()
            }
        }
    }

}


@Composable
fun LogoButton() {
    CompositionLocalProvider(
        LocalLogoImageSize provides dimensionResource(id = R.dimen.userAvatarSize)
    ) {
        CircularImage()
    }
}


@Composable
fun CircleLogo(imageId:Int = R.drawable.ic_facebook_f_logo) {
    CompositionLocalProvider(
        LocalLogoImageId provides imageId,
        LocalLogoImageSize provides dimensionResource(id = R.dimen.faceBookLogoSize)
    ) {
        CircularImage()
    }
}

@Composable
fun CircularImage() {
    Image(
        painter = painterResource(LocalLogoImageId.current),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,            // crop the image if it's not a square
        modifier = Modifier
            .size(LocalLogoImageSize.current)
            .clip(CircleShape)

    )
}


@Composable
fun storyInMainPage() {
    CompositionLocalProvider {
        StoryImageBanner()
    }
}

@Composable
fun storyInUserPage() {
    val imageId = R.drawable.girl
    val imageSize = StoryImageSize(imageHeight = 100.dp)
    CompositionLocalProvider(
        LocalStoryImageSize provides imageSize,
        LocalStoryImageId provides imageId
    ) {
        StoryImageBanner()
    }
}

@Composable
fun StoryImageBanner() {
    Image(
        painter = painterResource(MaterialTheme.storyImageId),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(MaterialTheme.StoryImageSize.imageWidth)
            .height(MaterialTheme.StoryImageSize.imageHeight)
            .clip(RoundedCornerShape(MaterialTheme.StoryImageSize.imageCorner))
    )
}


@Composable
fun PostImageBanner() {
    Image(
        painter = painterResource(MaterialTheme.postImageId),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(MaterialTheme.PostImageSize.imageWidth)
            .height(MaterialTheme.PostImageSize.imageHeight)
            .clip(RoundedCornerShape(MaterialTheme.PostImageSize.imageCorner))
    )
}


