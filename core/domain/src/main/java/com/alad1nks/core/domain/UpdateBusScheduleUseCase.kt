package com.alad1nks.core.domain

import com.alad1nks.core.data.repository.BusScheduleRepository
import com.alad1nks.core.model.RevisionResponse
import java.io.IOException
import javax.inject.Inject

class UpdateBusScheduleUseCase @Inject constructor(
    private val repository: BusScheduleRepository
) {
    suspend operator fun invoke(): RevisionResponse {
        return try {
            repository.updateBusSchedule()
        } catch (e: IOException) {
            return RevisionResponse.NETWORK_ERROR
        }
    }
}