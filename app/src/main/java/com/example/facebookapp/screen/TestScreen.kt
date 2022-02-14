package com.example.facebookapp.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookapp.R
import com.example.facebookapp.ui.theme.Purple200
import com.example.facebookapp.ui.theme.Teal200
import kotlin.math.ceil
import kotlin.random.Random

val localTitle = compositionLocalOf { "this is a title" }
val localTextColor = compositionLocalOf { Purple200 }

@Composable
fun ColorGrid() {
    val colors = listOf(
        Color.Gray,
        Color.Red,
        Color.Cyan,
        Color.Blue,
        Color.LightGray,
        Color.Yellow,
        Color.Magenta,
    )
    val rowCounts = listOf(3, 3, 1, 1, 1)
    var i = 0
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(rowCounts) { rowCount ->
            Row(
                modifier = Modifier
            ) {
                for (j in 0 until rowCount) {
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .weight(1f)
                            .background(colors[i++ % colors.count()])
                    )
                }
            }
        }
    }
}
@Composable
fun DifferentList() {
    LazyColumn {
        // Add a single item
        item {
            Text(text = "First item")
        }

        // Add 5 items
        items(5) { index ->
            Text(text = "Item: $index")
        }

        // Add another single item
        item {
            Text(text = "Last item")
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun GridList() {
    val list = (1..10).map { it.toString() }

    LazyColumn {
        itemsIndexed((1..5).map { it.toString() }) { _, row ->
            LazyRow {
                itemsIndexed(list) { _, column ->
                    Card(
                        backgroundColor = Color.LightGray,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Row: $row\nCol: $column",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun StaggeredVerticalGrid(
    modifier: Modifier = Modifier,
    maxColumnWidth: Dp,
    with:Int = 0,
    children: @Composable () -> Unit
) {
    Layout(
        content = children,
        modifier = modifier
    ) { measurables, constraints ->
        check(constraints.hasBoundedWidth) {
            "Unbounded width not supported"
        }
        val columns = ceil(constraints.maxWidth / maxColumnWidth.toPx()).toInt()
        val columnWidth = constraints.maxWidth / columns
        val itemConstraints = constraints.copy(maxWidth = columnWidth)
        val colHeights = IntArray(columns) { 0 } // track each column's height
        val placeables = measurables.map { measurable ->
            val column = shortestColumn(colHeights)
            val placeable = measurable.measure(itemConstraints)
            colHeights[column] += placeable.height
            placeable
        }

        val height = colHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
            ?: constraints.minHeight
        val newwidth = if (with == 0) constraints.maxWidth else with
        layout(
            width = newwidth,
            height = height
        ) {
            val colY = IntArray(columns) { 0 }
            placeables.forEach { placeable ->
                val column = shortestColumn(colY)
                placeable.place(
                    x = columnWidth * column,
                    y = colY[column]
                )
                colY[column] += placeable.height
            }
        }
    }
}

private fun shortestColumn(colHeights: IntArray): Int {
    var minHeight = Int.MAX_VALUE
    var column = 0
    colHeights.forEachIndexed { index, height ->
        if (height < minHeight) {
            minHeight = height
            column = index
        }
    }
    return column
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun NewTestHorizintalGrid() {

    StaggeredVerticalGrid(
        maxColumnWidth = 80.dp,
        modifier = Modifier.padding(4.dp)
    ) {
        val list = listOf(0, 9)
        repeat(10) {
            if (list.contains(it)) {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center) {
                    ColoredText(width = 40.dp)
                }
            } else {
                Box() {
                    ColoredText(width = 80.dp)
                }
            }
        }

    }
}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestHorizintalGrid() {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(1) { item ->
            ColoredText("E", 4.dp, 20.dp)
        }
        items(5) { item ->
            ColoredText(item.toString())
        }
    }
}

@Composable
fun ColoredText(
    item: String = "0", padding: Dp = 4.dp, width: Dp = 30.dp
) {
    Card(
        modifier = Modifier
            .padding(padding)
            .width(width),
        backgroundColor = Color(
            red = Random.nextInt(0, 255),
            green = Random.nextInt(0, 255),
            blue = Random.nextInt(0, 255)
        )
    ) {
        Text(
            text = item.toString(),
            fontSize = 42.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(24.dp)
        )
    }
}

/**
 * First title has default variable of localTitle and localTextColor
 *
 */
@Composable
fun firstTitle() {
    CompositionLocalProvider {
        ScreenText()
    }
}

/**
 * Second title with CompositionLocalProvider we can change the localText and localColorText
 * and see another results
 *
 */
@Composable
fun secondTitle() {
    CompositionLocalProvider(
        localTitle provides ("secondTitle"),
        localTextColor provides Teal200
    ) {
        ScreenText()
    }
}

@Composable
fun ScreenText() {
    Text(text = localTitle.current, color = localTextColor.current)
}