package com.petsup.ui.view.fragment

import android.Manifest
import android.content.Context
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.petsup.R
import com.petsup.databinding.FragmentHomeBinding
import com.petsup.models.petshop.Petshop
import com.petsup.ui.view.adapter.PetshopsAdapter
import com.petsup.ui.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel = HomeViewModel()
    private lateinit var locationRequest: LocationRequest

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        if (ActivityCompat.checkSelfPermission(super.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isGPSEnabled()) {
                LocationServices.getFusedLocationProviderClient(super.requireActivity())
                    .requestLocationUpdates(locationRequest, object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            super.onLocationResult(locationResult)
                            LocationServices.getFusedLocationProviderClient(requireActivity())
                                .removeLocationUpdates(this)
                            if (locationResult != null && locationResult.locations.size > 0) {
                                val index = locationResult.locations.size - 1
                                val latitude = locationResult.locations[index].latitude
                                val longitude = locationResult.locations[index].longitude
                                Log.i("COORDINATES", latitude.toString())
                                Log.i("COORDINATES", longitude.toString())
                            }
                        }
                    }, Looper.getMainLooper())
            } else {
                turnOnGPS()
            }
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        setObservers()
        getPetshops()

        val filterButton = binding.filterButton

//        filterButton.setOnClickListener {
//            PopupMenu(context, filterButton).apply {
//                menuInflater.inflate(R.menu.petshop_filter_menu, this.menu)
//                setOnMenuItemClickListener {
//                    when (it.itemId) {
//                        R.id.filter_distance -> {
//
//                        }
//                        R.id.filter_price -> {
//
//                        }
//                        R.id.filter_rating -> {
//
//                        }
//                        R.id.filter_favorite -> {
//
//                        }
//                        else -> {
//
//                        }
//                    }
//                }
//            }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setObservers() {
        viewModel.petshopList.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }
    }

    private fun initRecyclerView(petshops: List<Petshop>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = setAdapter(petshops)
    }

    private fun setAdapter(petshops: List<Petshop>): PetshopsAdapter {
        return PetshopsAdapter(petshops, requireContext())
    }

    private fun getPetshops() = viewModel.getPetshops()

    private fun turnOnGPS() {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        val result = LocationServices.getSettingsClient(requireActivity())
            .checkLocationSettings(builder.build())
        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
                Toast.makeText(requireContext(), "O GPS já está ativado", Toast.LENGTH_SHORT)
                    .show()
            } catch (e: ApiException) {
                when (e.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = e as ResolvableApiException
                        resolvableApiException.startResolutionForResult(requireActivity(), 2)
                    } catch (ex: SendIntentException) {
                        ex.printStackTrace()
                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
                }
            }
        }
    }

    private fun isGPSEnabled(): Boolean {
        var locationManager: LocationManager? = null
        var isEnabled = false
        if (locationManager == null) {
            locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        }
        isEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        return isEnabled
    }

}