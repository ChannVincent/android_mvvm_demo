package chann.vincent.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import chann.vincent.mvvm.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(null, navigationController)
    }

    fun setupNavigation() {
        setSupportActionBar(toolbar)
        navigationController = Navigation.findNavController(this, R.id.navigation_host_fragment)
        bottom_navigation.setupWithNavController(navigationController)
        NavigationUI.setupActionBarWithNavController(this, navigationController)
    }
}
