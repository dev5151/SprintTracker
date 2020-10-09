package com.orion.sprinttracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.sprinttracker.data.Run
import com.orion.sprinttracker.repository.RunRepository
import kotlinx.coroutines.launch

class StatisticsViewModel @ViewModelInject constructor(private val runRepository: RunRepository) :
    ViewModel() {

    fun insertRun(run: Run) = viewModelScope.launch {
        runRepository.insertRun(run)
    }

    fun deleteRun(run: Run) = viewModelScope.launch {
        runRepository.deleteRun(run)
    }
}