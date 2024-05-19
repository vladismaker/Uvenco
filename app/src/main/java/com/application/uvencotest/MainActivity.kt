package com.application.uvencotest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.application.uvencotest.dagger.DaggerApplicationComponent
import com.application.uvencotest.dagger.MainViewModelFactory
import com.application.uvencotest.navigation.AppNavHost
import com.application.uvencotest.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerApplicationComponent.create().inject(this)

        val mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        mainViewModel.setDataInArrayCupsList()

        setContent {
            Log.d("debug", "Задаем setContent")
            val navController = rememberNavController()

            AppNavHost(navController, mainViewModel)
        }
    }
}