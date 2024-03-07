package com.example.ifoodclone.presentation.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.ifoodclone.R
import com.example.ifoodclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inicializarNavegacao()
    }

    private fun inicializarNavegacao() {

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerPrincipal) as NavHostFragment
        val navControler = navHostFragment.navController
        //val idFragment = navControler.currentDestination?.id

        NavigationUI.setupWithNavController(
            navigationBarView = binding.bottomNavigationPrincipal,
            navController = navControler
        )
    }
}