package com.example.bravocalendar

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bravocalendar.databinding.LoginFragmentBinding
import com.example.bravocalendar.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val args: ProfileFragmentArgs by navArgs()
    private lateinit var binding: ProfileFragmentBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.emailLiveData.value = args.email
        viewModel.nameLiveData.value = args.name
        viewModel.context = requireContext()
        viewModel.navigationController = findNavController()
        binding = ProfileFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

}