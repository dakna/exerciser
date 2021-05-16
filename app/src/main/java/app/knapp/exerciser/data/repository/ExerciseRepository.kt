package app.knapp.exerciser.data.repository

import app.knapp.exerciser.data.api.ExerciseAPI
import app.knapp.exerciser.ui.main.ExerciseViewData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExerciseRepository(private val exerciseAPI: ExerciseAPI) {

    fun getExerciseListForToday() : Flow<List<ExerciseViewData>> {
        return flow {
            val response = exerciseAPI.getCurrentDayExerciseList()
            val result = mutableListOf<ExerciseViewData>()
            if (response.isSuccessful) {
                response.body()?.let { responseList ->
                    result.addAll(responseList.map { response ->
                        ExerciseViewData(
                            response.id,
                            response.name,
                            response.coverImageURL,
                            response.videoURL,
                            false // todo: replace with local DB lookup
                        ) })
                }
            } else error("API call failed") // todo: replace with sealed result class for success/error
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}