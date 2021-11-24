package ru.radiknasybullin.chatapp.presentation.ui.fragments.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.radiknasybullin.chatapp.R
import ru.radiknasybullin.chatapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var mBinding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return mBinding.root
    }

}