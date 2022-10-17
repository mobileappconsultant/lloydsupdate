package com.android.gameofthrones.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.android.gameofthrones.ui.composable.AppBackground
import com.android.gameofthrones.ui.composable.CharacterList
import com.android.gameofthrones.ui.composable.ErrorPage
import com.android.gameofthrones.ui.composable.FullScreenProgress
import com.android.gameofthrones.ui.composable.SearchComponent
import com.android.gameofthrones.ui.model.Character
import com.android.gameofthrones.ui.screens.viewmodel.HomeViewModel
import com.android.gameofthrones.utils.showToast

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(navigateToDetails: (Character) -> Unit) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    AppBackground {
        val viewState by homeViewModel.viewState.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            SearchComponent {
                homeViewModel.search(it)
            }
            Box(Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp)) {
                when (viewState) {
                    HomeViewModel.ViewState.Loading -> {
                        FullScreenProgress(modifier = Modifier.fillMaxSize())
                    }
                    is HomeViewModel.ViewState.Success -> {
                        val state = viewState as HomeViewModel.ViewState.Success
                        CharacterList(state.data, navigateToDetails)
                        state.errorMessage?.let {
                            LocalContext.current.showToast(it)
                        }
                    }
                    is HomeViewModel.ViewState.Error -> {
                        ErrorPage(
                            message = (viewState as HomeViewModel.ViewState.Error).message,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            homeViewModel.getAllDataFromApi()
                        }
                    }
                    HomeViewModel.ViewState.Idle -> {
                    }
                }
            }
        }
    }
}
