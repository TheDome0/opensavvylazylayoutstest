package org.example.opensavvylazylayoutstest.ballast

object HomeContract {
    data class State(
        val title: String = "Hello, World!",
        val list: List<Int> = List(100) { it },
        val checked: List<Int> = emptyList(),
    )

    sealed interface Inputs {
        data class SetTitle(val title: String) : Inputs
        data class ToggleChecked(val id: Int) : Inputs
    }

    sealed interface Events {

    }
}
