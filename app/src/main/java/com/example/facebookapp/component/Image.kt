package com.example.facebookapp.component

import android.provider.SyncStateContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebookapp.R

@Composable
fun circularImage(imageId: Int = R.drawable.portrait, size: Int = 64, hasStory:Boolean = false) {
    Image(
        painter = painterResource(imageId),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,            // crop the image if it's not a square
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)                       // clip to the circle shape
            .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
    )
}

@Preview
@Composable
fun PostImageBanner(imageId: Int = R.drawable.portrait) {
    Image(
        painter = painterResource(imageId),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.postImageWidth))
            .height(dimensionResource(id = R.dimen.postImageHeight))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.postImageCorner)))
    )
}

@Preview
@Composable
fun StoryImageBanner(imageId: Int = R.drawable.portrait) {
    Image(
        painter = painterResource(imageId),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.storyImageWidth))
            .height(dimensionResource(id = R.dimen.storyImageHeight))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.storyImageCorner)))
    )
}

