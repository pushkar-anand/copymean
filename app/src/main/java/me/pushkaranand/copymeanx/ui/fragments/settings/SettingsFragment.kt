package me.pushkaranand.copymeanx.ui.fragments.settings


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import kotlinx.android.synthetic.main.activity_base.*
import me.pushkaranand.copymeanx.R
import me.pushkaranand.copymeanx.services.ClipboardMonitor

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var clipMonitorSwitch: SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_settings, rootKey)
        clipMonitorSwitch = findPreference(getString(R.string.key_clipboard_monitor))!!
        setupListeners()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.base_fragment)
        bottomNavView.setupWithNavController(navController)
    }

    private fun setupListeners() {
        clipMonitorSwitch.setOnPreferenceChangeListener { _, newValue ->
            val state = newValue as Boolean
            val clipMonitorIntent = Intent(requireContext(), ClipboardMonitor::class.java)
            if (state) {
                if (!ClipboardMonitor.isMonitoringOn) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        requireContext().startForegroundService(clipMonitorIntent)
                    } else {
                        requireContext().startService(clipMonitorIntent)
                    }
                }
            } else {
                requireContext().stopService(clipMonitorIntent)
            }
            true
        }
    }
}
