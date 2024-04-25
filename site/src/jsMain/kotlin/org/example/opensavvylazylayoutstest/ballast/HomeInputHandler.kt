package org.example.opensavvylazylayoutstest.ballast

import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope

class HomeInputHandler : InputHandler<
        HomeContract.Inputs,
        HomeContract.Events,
        HomeContract.State> {
    override suspend fun InputHandlerScope<
            HomeContract.Inputs,
            HomeContract.Events,
            HomeContract.State>.handleInput(
        input: HomeContract.Inputs,
    ): Unit = when (input) {
        is HomeContract.Inputs.SetTitle -> updateState { state ->
            state.copy(title = input.title)
        }

        is HomeContract.Inputs.ToggleChecked -> updateState { state ->
            if (state.checked.contains(input.id)) {
                state.copy(checked = state.checked - input.id)
            } else {
                state.copy(checked = state.checked + input.id)
            }
        }
    }
}
