package com.example.bravocalendar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bravocalendar.databinding.CreateEventFragmentBinding
import com.example.bravocalendar.databinding.RegisterFragmentBinding

class CreateEventFragment : Fragment() {

    companion object {
        fun newInstance() = CreateEventFragment()
    }

    private val args: CreateEventFragmentArgs by navArgs()
    private lateinit var viewModel: CreateEventViewModel
    private lateinit var binding: CreateEventFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[CreateEventViewModel::class.java]
        viewModel.context = requireContext()
        viewModel.date = args.date
        viewModel.email = args.email
        viewModel.navigationController = findNavController()
        binding = CreateEventFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

}