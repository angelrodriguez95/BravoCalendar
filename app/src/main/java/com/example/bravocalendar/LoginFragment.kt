package com.example.bravocalendar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.bravocalendar.api.ApiInterface
import com.example.bravocalendar.data.WeatherData
import com.example.bravocalendar.databinding.LoginFragmentBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.context = requireContext()
        viewModel.navigationController = findNavController()
        binding = LoginFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        //viewModel.cleanDB()
//        viewModel.fillDB()
        viewModel.checkDB()
        return binding.root
    }
}