package app.knapp.exerciser.data.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit


@ExperimentalSerializationApi
val repositoryModule = module {
    factory {
        ExerciseRepository(get())
    }
}