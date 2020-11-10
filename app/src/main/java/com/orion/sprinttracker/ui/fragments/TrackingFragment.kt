package com.orion.sprinttracker.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.orion.sprinttracker.R
import com.orion.sprinttracker.databinding.FragmentTrackingBinding
import com.orion.sprinttracker.services.TrackingService
import com.orion.sprinttracker.ui.viewmodels.MainViewModel
import com.orion.sprinttracker.utils.Constants.Companion.ACTION_START_OR_RESUME_SERVICE
import com.orion.sprinttracker.utils.Constants.Companion.MAP_VIEW_BUNDLE_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tracking.*
import javax.inject.Inject

const val CANCEL_DIALOG_TAG = "CancelDialog"


@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    @set:Inject
    var weight: Float = 80f

    private val mainViewModel: MainViewModel by viewModels()

    private var binding: FragmentTrackingBinding? = null

    private var map: GoogleMap? = null

    private var isTracking = false
    private var curTimeInMillis = 0L
    private var pathPoints = mutableListOf<MutableList<LatLng>>()

    private var menu: Menu? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracking, container, false);
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapViewBundle = savedInstanceState?.getBundle(MAP_VIEW_BUNDLE_KEY)

        // binding?.mainViewModel = mainViewModel

        mapView.onCreate(mapViewBundle)

        mapView.getMapAsync {
            map = it
        }

        btnToggleRun.setOnClickListener {
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }

    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    private fun sendCommandToService(action: String) {
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }
    }
}