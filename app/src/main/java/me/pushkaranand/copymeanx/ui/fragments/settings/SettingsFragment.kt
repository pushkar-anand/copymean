package me.pushkaranand.copymeanx.ui.fragments.settings


import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import kotlinx.android.synthetic.main.activity_base.*
import me.pushkaranand.copymeanx.R
import me.pushkaranand.copymeanx.utils.AlarmHelper
import me.pushkaranand.copymeanx.utils.ClipboardHelper

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var clipMonitorSwitch: SwitchPreference
    private lateinit var dailyNotificationSwitch: SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_settings, rootKey)
        initVars()
        setupListeners()
    }

    private fun initVars() {
        clipMonitorSwitch = findPreference(getString(R.string.key_clipboard_monitor))!!
        dailyNotificationSwitch = findPreference(getString(R.string.key_daily_word_notify))!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.base_fragment)
        val bottomNav = requireActivity().bottomNavView
        bottomNav.setupWithNavController(navController)
    }

    private fun setupListeners() {
        setupClipMonitorSwitchListener()
        setupDailyNotificationSwitchListener()
    }

    private fun setupClipMonitorSwitchListener() {
        clipMonitorSwitch.setOnPreferenceChangeListener { _, newValue ->
            val state = newValue as Boolean
            if (state) {
                ClipboardHelper.startClipboardMonitoring(requireContext())
            } else {
                ClipboardHelper.stopClipboardMonitoring(requireContext())
            }
            true
        }
    }

    private fun setupDailyNotificationSwitchListener() {
        val alarmHelper = AlarmHelper(requireContext())
        dailyNotificationSwitch.setOnPreferenceChangeListener { _, newValue ->
            val state = newValue as Boolean
            if (state) {
                alarmHelper.setDailyAlarm()
            } else {
                alarmHelper.disableDailyAlarm()
            }
            true
        }
    }
}
