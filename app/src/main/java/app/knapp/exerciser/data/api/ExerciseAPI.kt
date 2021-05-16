package app.knapp.exerciser.data.api

import app.knapp.exerciser.data.api.response.ExerciseApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExerciseAPI {

    @GET(NetworkConfig.EXERCISE_API_CURRENT_DAY)
    suspend fun getCurrentDayExerciseList(): Response<List<ExerciseApiResponse>>
}