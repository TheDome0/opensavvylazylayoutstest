package org.example.opensavvylazylayoutstest.ballast

import com.copperleaf.ballast.core.BasicViewModel
import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.build
import com.copperleaf.ballast.withViewModel
import kotlinx.coroutines.CoroutineScope

class HomeViewModel(
    coroutineScope: CoroutineScope,
) : BasicViewModel<
        HomeContract.Inputs,
        HomeContract.Events,
        HomeContract.State>(
    coroutineScope = coroutineScope,
    config = BallastViewModelConfiguration.Builder()
        .withViewModel(
            inputHandler = HomeInputHandler(),
            initialState = HomeContract.State(),
            name = "Home",
        )
        .build(),
    eventHandler = HomeEventHandler(),
)
