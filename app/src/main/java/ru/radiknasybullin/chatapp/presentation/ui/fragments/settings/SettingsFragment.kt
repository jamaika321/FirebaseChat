package ru.radiknasybullin.chatapp.presentation.ui.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.radiknasybullin.chatapp.R
import ru.radiknasybullin.chatapp.data.utilits.replaceFragment
import ru.radiknasybullin.chatapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var mBinding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSettingsBinding.inflate(inflater, container, false)

        mBinding.profileSettings.setOnClickListener {
            replaceFragment(ProfileFragment())
        }

        return mBinding.root
    }


}