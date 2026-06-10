package com.jetpack.compose.github.github.cruise.ui.features.settings

import androidx.lifecycle.ViewModel
import com.jetpack.compose.github.github.cruise.data.preferences.ThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * ViewModel for Settings screen
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themePreferences: ThemePreferences
) : ViewModel() {

    val isDarkMode: StateFlow<Boolean> = themePreferences.isDarkMode

    fun setDarkMode(enabled: Boolean) {
        themePreferences.setDarkMode(enabled)
    }
}
