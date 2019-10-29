package me.pushkaranand.copymeanx.ui.fragments.settings


import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.main.activity_base.*
import me.pushkaranand.copymeanx.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_settings, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.base_fragment)
        bottomNavView.setupWithNavController(navController)
    }
}
