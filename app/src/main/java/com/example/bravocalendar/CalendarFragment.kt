package com.example.bravocalendar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bravocalendar.api.ApiInterface
import com.example.bravocalendar.data.WeatherData
import com.example.bravocalendar.databinding.CalendarFragmentBinding
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class CalendarFragment : Fragment() {

    companion object {
        fun newInstance() = CalendarFragment()
    }

    private val args: CalendarFragmentArgs by navArgs()
    private lateinit var viewModel: CalendarViewModel
    private lateinit var binding: CalendarFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[CalendarViewModel::class.java]

        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.weatherbit.io/").build().create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<WeatherData?> {
            override fun onResponse(call: Call<WeatherData?>, response: Response<WeatherData?>) {
                val responseBody = response.body()!!
                viewModel.tempLiveData.value = responseBody.data[0].app_temp.toString() + "°C"
                Log.v("WEATHERAPI", Gson().toJson(responseBody.data))
            }

            override fun onFailure(call: Call<WeatherData?>, t: Throwable) {
                Log.v("WEATHERAPI", t.message!!)
            }
        })

        viewModel.name = args.name
        viewModel.email = args.email
        viewModel.nameLiveData = MutableLiveData("Hello ${args.name}")
        viewModel.context = requireContext()
        viewModel.navigationController = findNavController()
        binding = CalendarFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendarView: CalendarView = view.findViewById(R.id.calendarView)

        calendarView.setOnDateChangeListener { _, year, month, day ->
            viewModel.dateClicked(day, month, year)
        }

        view.findViewById<ImageView>(R.id.profilePicture).setOnClickListener{
            viewModel.profileClicked()
        }
    }
}