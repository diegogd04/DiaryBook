package edu.iesam.diarybook

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val auth: FirebaseAuth by inject()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigationBar()
        checkSession()
    }

    private fun setUpBottomNavigationBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        bottomNavView.setupWithNavController(navController)

        bottomNavView.setOnItemSelectedListener { item ->
            setAccountView(item, navController)
            true
        }
    }

    private fun setAccountView(item: MenuItem, navController: NavController) {
        val user = auth.currentUser
        val isLoggedIn = user != null && !user.isAnonymous

        when (item.itemId) {
            R.id.login_fragment, R.id.profile_fragment -> {
                val destination = if (isLoggedIn) {
                    R.id.profile_fragment
                } else {
                    R.id.login_fragment
                }
                navController.navigate(destination)
            }

            else -> {
                if (isLoggedIn) {
                    navController.navigate(item.itemId)
                } else {
                    showLoginSnackbar()
                }
            }
        }
    }

    private fun checkSession() {
        val user = auth.currentUser
        val destination = if (user != null && !user.isAnonymous) {
            R.id.activity_list_fragment
        } else {
            R.id.login_fragment
        }
        navController.navigate(destination)
    }

    private fun showLoginSnackbar() {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Debes iniciar sesión para acceder a esta sección.",
            Snackbar.LENGTH_LONG
        ).setAction("Iniciar sesión") {
            navController.navigate(R.id.login_fragment)
        }.show()
    }
}