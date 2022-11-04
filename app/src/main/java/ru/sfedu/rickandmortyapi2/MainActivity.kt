package ru.sfedu.rickandmortyapi2

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sfedu.rickandmortyapi2.databinding.ActivityMainBinding
import ru.sfedu.rickandmortyapi2.fragments.CharacterFragment
import ru.sfedu.rickandmortyapi2.fragments.EpisodeFragment
import ru.sfedu.rickandmortyapi2.fragments.LocationFragment
import ru.sfedu.rickandmortyapi2.fragments.SupportFragment
import ru.sfedu.rickandmortyapi2.viewmodel.CharacterViewModel
import ru.sfedu.rickandmortyapi2.viewmodel.EpisodeViewModel
import ru.sfedu.rickandmortyapi2.viewmodel.LocationViewModel
import java.lang.System.runFinalizersOnExit
import kotlin.system.exitProcess


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val supportFragment = SupportFragment()

    private val characterViewModel: CharacterViewModel by viewModels()
    private val episodeViewModel: EpisodeViewModel by viewModels()
    private val locationViewModel: LocationViewModel by viewModels()

    private var initLoadings = false

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        getClickBottomNavigation()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = null
    }

    @Suppress("DEPRECATION")
    private fun getClickBottomNavigation() {
        val bottomNavigationItemCharacters = findViewById<View>(R.id.character_page)
        val bottomNavigationItemLocations = findViewById<View>(R.id.location_page)
        val bottomNavigationItemEpisodes = findViewById<View>(R.id.episode_page)
        bottomNavigationItemCharacters.setBackgroundColor(Color.BLUE)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.character_page -> {
                    bottomNavigationItemLocations.setBackgroundColor(resources.getColor(R.color.secondary_dark_blue))
                    bottomNavigationItemEpisodes.setBackgroundColor(resources.getColor(R.color.secondary_dark_blue))
                    bottomNavigationItemCharacters.setBackgroundColor(Color.BLUE)
                    supportFragment.startFragment(CharacterFragment(), this)
                }
                R.id.location_page -> {
                    bottomNavigationItemEpisodes.setBackgroundColor(resources.getColor(R.color.secondary_dark_blue))
                    bottomNavigationItemCharacters.setBackgroundColor(resources.getColor(R.color.secondary_dark_blue))
                    bottomNavigationItemLocations.setBackgroundColor(Color.BLUE)
                    supportFragment.startFragment(LocationFragment(), this)
                }
                R.id.episode_page -> {
                    bottomNavigationItemCharacters.setBackgroundColor(resources.getColor(R.color.secondary_dark_blue))
                    bottomNavigationItemLocations.setBackgroundColor(resources.getColor(R.color.secondary_dark_blue))
                    bottomNavigationItemEpisodes.setBackgroundColor(Color.BLUE)
                    supportFragment.startFragment(EpisodeFragment(), this)
                }
                //else -> false
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        if (!initLoadings) {
            lifecycleScope.launch(Dispatchers.IO) {
                delay(1500)
                characterViewModel.getInfo()
                locationViewModel.getInfo()
                episodeViewModel.getInfo()
                initLoadings = true
                withContext(Dispatchers.Main) {
                    binding.progressBarInit.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroy() {
        moveTaskToBack(true)
        super.onDestroy()
        runFinalizersOnExit(true)
        exitProcess(0)
    }

}