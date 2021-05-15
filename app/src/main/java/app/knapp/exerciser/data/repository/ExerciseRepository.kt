package app.knapp.exerciser.data.repository

import app.knapp.exerciser.data.api.ExerciseAPI
import app.knapp.exerciser.data.api.response.ExerciseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExerciseRepository(private val exerciseAPI: ExerciseAPI) {

    fun getExerciseListForToday() : Flow<List<ExerciseData>> {
        return flow {
            val response = exerciseAPI.getCurrentDayExerciseList()
            val result = mutableListOf<ExerciseData>()
            if (response.isSuccessful) {
                response.body()?.let {
                    result.addAll(it)
                }
            } else error("API call failed")
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}