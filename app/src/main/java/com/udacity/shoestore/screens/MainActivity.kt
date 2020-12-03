package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.viewmodels.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        navController.addOnDestinationChangedListener { controller, _, _ ->
            invalidateOptionsMenu()
            val shouldShowUpButton = controller.previousBackStackEntry != null
            supportActionBar?.setDisplayHomeAsUpEnabled(shouldShowUpButton)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (navController.currentDestination?.id == navController.graph.startDestination) {
            return false
        }
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_logout -> {
                navController.navigate(LoginFragmentDirections.actionGlobalLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
