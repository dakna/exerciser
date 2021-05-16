package app.knapp.exerciser.ui.workout

import android.util.Log
import androidx.lifecycle.*
import app.knapp.exerciser.data.repository.ExerciseRepository

class WorkoutViewModel(exerciseRepository: ExerciseRepository) : ViewModel() {

    private val _workoutProgress = MutableLiveData(mutableMapOf<String, Boolean>())
    val workoutProgress: LiveData<MutableMap<String, Boolean>> = _workoutProgress

    fun markWorkoutAsSkipped(mediaId: String) {
        Log.d("TAG", "markWorkoutAsSkipped: mediaId = $mediaId")
        workoutProgress.value?.put(mediaId, true)
    }
}