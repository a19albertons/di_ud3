package com.example.masterrollerdice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    // Companion
    companion object {
        // Al estar en un companion object, esta variable se puede acceder
        // desde cualquier parte de la app usando MainActivity.isVibrationEnabled
        var isVibrationEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val smallestWidth = resources.configuration.smallestScreenWidthDp
        Log.d("DeviceMetrics", "Mi Smallest Width (sw) es: ${smallestWidth}dp")

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // BottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        val bottmNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottmNav.setupWithNavController(navHostFragment.navController)

        startService(Intent(this, MusicService::class.java))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Aquí es donde manejas los clics
        when (item.itemId) {
            // ACCIÓN 1: Limpiar Historial
            R.id.action_limpiar_historial -> {
                // Llama al metodo de historial.kt directamente
                borrarHistorial(this,navController)

                // Notifica que hace algo
                Toast.makeText(this, "Limpiar Historial", Toast.LENGTH_SHORT).show()
                return true // Indica que hemos gestionado el clic
            }

            // ACCIÓN 2: Cambiar Tema
            R.id.action_cambiar_tema -> {
                val currentNightMode = AppCompatDelegate.getDefaultNightMode()
                if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                Toast.makeText(this, "Cambiar Tema", Toast.LENGTH_SHORT).show()
                return true
            }

            // ACCIÓN 3: Sonido On/Off
            R.id.action_sonido -> {
                item.isChecked = !item.isChecked
                if (item.isChecked) {
                    startService(Intent(this, MusicService::class.java))
                    Toast.makeText(this, "Sonido Activado", Toast.LENGTH_SHORT).show()
                    // GUARDAR PREFERENCIA (SharedPreferences)
                } else {
                    stopService(Intent(this, MusicService::class.java))
                    Toast.makeText(this, "Sonido Desactivado", Toast.LENGTH_SHORT).show()
                    // GUARDAR PREFERENCIA
                }
                return true
            }

            // ACCIÓN 4: Vibración On/Off
            R.id.action_vibracion -> {
                item.isChecked = !item.isChecked
                if (item.isChecked) {
                    isVibrationEnabled = true

                    Toast.makeText(this, "Vibración Activada", Toast.LENGTH_SHORT).show()
                } else {
                    isVibrationEnabled = false
                    Toast.makeText(this, "Vibración Desactivada", Toast.LENGTH_SHORT).show()
                }
                return true
            }
        }

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, MusicService::class.java))
    }
}