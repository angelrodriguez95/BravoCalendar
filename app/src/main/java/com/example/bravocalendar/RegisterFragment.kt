package com.example.bravocalendar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bravocalendar.databinding.LoginFragmentBinding
import com.example.bravocalendar.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        viewModel.context = requireContext()
        viewModel.navigationController = findNavController()
        binding = RegisterFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }
}