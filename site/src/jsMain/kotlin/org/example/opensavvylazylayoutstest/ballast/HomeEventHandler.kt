package org.example.opensavvylazylayoutstest.ballast

import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope

class HomeEventHandler : EventHandler<
        HomeContract.Inputs,
        HomeContract.Events,
        HomeContract.State> {
    override suspend fun EventHandlerScope<
            HomeContract.Inputs,
            HomeContract.Events,
            HomeContract.State>.handleEvent(
        event: HomeContract.Events,
    ): Unit = when (event) {
        else -> {}
    }
}
