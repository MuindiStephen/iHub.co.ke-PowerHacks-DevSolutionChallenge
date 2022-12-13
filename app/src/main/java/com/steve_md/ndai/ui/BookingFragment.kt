package com.steve_md.ndai.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.steve_md.ndai.R
import com.steve_md.ndai.databinding.FragmentBookingBinding


class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBookingBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val spinner: Spinner = findViewById(R.id.spinnerDestination)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.destination_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerDestination.adapter = adapter
        }

        binding.buttonPayFare.setOnClickListener {
            AlertDialog.Builder(
                requireActivity()
            ).setCancelable(false)
                .setTitle("Pay Now")
                .setMessage("Are you sure you want to pay now? \n")
           .setPositiveButton(
                "Yes"
           ) { dialog, which ->

              // STK push logic
               findNavController().navigate(R.id.action_dashboardFragment_to_bookingFragment)

           }
                .setNegativeButton(
                "No"
                ) { dialog, which -> dialog.dismiss() }
                .create()

                .show()

        }
        }
    }

}