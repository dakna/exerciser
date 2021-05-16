package app.knapp.exerciser.ui.main

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.knapp.exerciser.data.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(exerciseRepository: ExerciseRepository) : ViewModel() {

    val exerciseList = exerciseRepository.getExerciseListForToday().asLiveData(Dispatchers.IO)
    val exerciseCount = Transformations.map(exerciseList) {
        it.size.toString()
    }
}