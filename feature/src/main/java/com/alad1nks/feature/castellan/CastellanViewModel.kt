package com.alad1nks.feature.castellan

import androidx.lifecycle.ViewModel
import com.alad1nks.feature.castellan.CastellanDefaults.firstBuildingGraphic
import com.alad1nks.feature.castellan.CastellanDefaults.otherBuildingGraphic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CastellanViewModel @Inject constructor() : ViewModel() {
    private val _graphic: MutableStateFlow<List<GraphicItem>> =
        MutableStateFlow(firstBuildingGraphic)
    val graphic: StateFlow<List<GraphicItem>> get() = _graphic.asStateFlow()

    val day: StateFlow<Int>
        get() = MutableStateFlow(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))

    fun updateBuilding(
        buildingIndex: Int
    ) {
        _graphic.update {
            getGraphic(buildingIndex)
        }
    }

    private fun getGraphic(index: Int): List<GraphicItem> =
        if (index == 0) firstBuildingGraphic else otherBuildingGraphic
}