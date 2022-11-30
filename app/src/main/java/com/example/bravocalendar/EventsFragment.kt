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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.bravocalendar.adapter.CardAdapter
import com.example.bravocalendar.databinding.EventsFragmentBinding
import com.example.bravocalendar.databinding.LoginFragmentBinding
import com.example.bravocalendar.model.AppDatabase
import com.example.bravocalendar.model.Event
import com.example.bravocalendar.model.User
import com.example.bravocalendar.model.eventList
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Date
import java.util.*

class EventsFragment : Fragment() {
    companion object {
        fun newInstance() = EventsFragment()
    }
    private val args: EventsFragmentArgs by navArgs()
    private lateinit var viewModel: EventsViewModel
    private lateinit var binding: EventsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]
        viewModel.context = requireContext()
        viewModel.navigationController = findNavController()

        binding = EventsFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        viewModel.date = args.date
        viewModel.email = args.email
        viewModel.binding = binding
        viewModel.setEvents()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = args.date;
        val title: TextView = view.findViewById(R.id.dateTextView)
        val monthName = arrayOf(
            "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"
        )
        val text = "${date.day} ${monthName[date.month]} ${date.year}"
        title.text = text
    }

}