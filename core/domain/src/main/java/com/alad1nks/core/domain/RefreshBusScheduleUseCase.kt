package com.alad1nks.core.domain

import com.alad1nks.core.data.repository.BusScheduleRepository
import com.alad1nks.core.model.RevisionResponse
import javax.inject.Inject

class RefreshBusScheduleUseCase @Inject constructor(
    private val repository: BusScheduleRepository
) {
    suspend operator fun invoke(): RevisionResponse = repository.refreshBusSchedule()
}