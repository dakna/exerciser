package app.knapp.exerciser.ui.main

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.knapp.exerciser.data.api.response.ExerciseData
import app.knapp.exerciser.data.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(exerciseRepository: ExerciseRepository) : ViewModel() {
    private val _testState = MutableStateFlow("hello world")
    val testState: StateFlow<String> = _testState

    val exerciseList = exerciseRepository.getExerciseListForToday().asLiveData(Dispatchers.IO)
    val exerciseCount = Transformations.map(exerciseList) {
        it.size.toString()
    }
}