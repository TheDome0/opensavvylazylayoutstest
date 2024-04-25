package org.example.opensavvylazylayoutstest.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.Checkbox
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import opensavvy.compose.lazy.LazyColumn
import org.example.opensavvylazylayoutstest.ballast.HomeContract
import org.example.opensavvylazylayoutstest.ballast.HomeViewModel
import org.example.opensavvylazylayoutstest.components.layouts.PageLayout
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import kotlin.random.Random

// Container that has a tagline and grid on desktop, and just the tagline on mobile
val HeroContainerStyle by ComponentStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

// A demo grid that appears on the homepage because it looks good
val HomeGridStyle by ComponentStyle.base {
    Modifier
        .gap(0.5.cssRem)
        .width(70.cssRem)
        .height(18.cssRem)
}

private val GridCellColorVar by StyleVariable<Color>()
val HomeGridCellStyle by ComponentStyle.base {
    Modifier
        .backgroundColor(GridCellColorVar.value())
        .boxShadow(blurRadius = 0.6.cssRem, color = GridCellColorVar.value())
        .borderRadius(1.cssRem)
}

@Composable
private fun GridCell(color: Color, row: Int, column: Int, width: Int? = null, height: Int? = null) {
    Div(
        HomeGridCellStyle.toModifier()
            .setVariable(GridCellColorVar, color)
            .gridItem(row, column, width, height)
            .toAttrs()
    )
}

@Page
@Composable
fun HomePage() {
    val scope = rememberCoroutineScope()
    val vm = remember(scope) { HomeViewModel(scope) }
    val state by vm.observeStates().collectAsState()
    val list = remember { List(200) { it } }
    var checked by remember { mutableStateOf(emptyList<Int>()) }

    PageLayout("Home") {
        Title({ state.title }) { vm.trySend(HomeContract.Inputs.SetTitle("Hello, Compose! ${Random.nextInt()}")) }
        List2({ state.list }, { state.checked }) { vm.trySend(HomeContract.Inputs.ToggleChecked(it)) }
//        List({ list }, { checked }) { if (it in checked) checked -= it else checked += it }
    }
}

@Composable
private fun Title(title: () -> String, editTitle: () -> Unit) {
    Column {
        Text(title())
        Button(onClick = { editTitle() }) {
            Text("Change Title")
        }
    }
}

@Composable
private fun List(list: () -> List<Int>, checked: () -> List<Int>, onCheckedChange: (Int) -> Unit) {
    LazyColumn {
        items(list()) { card ->
            Row {
                Checkbox(checked = card in checked(), onCheckedChange = { onCheckedChange(card) })
                Text(card.toString())
            }
        }
    }
}

@Composable
private fun List2(list: () -> List<Int>, checked: () -> List<Int>, onCheckedChange: (Int) -> Unit) {
    Column {
        list().forEach { card ->
            Row {
                Checkbox(checked = card in checked(), onCheckedChange = { onCheckedChange(card) })
                Text(card.toString())
            }
        }
    }
}