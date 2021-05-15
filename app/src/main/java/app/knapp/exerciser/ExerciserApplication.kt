package app.knapp.exerciser

import android.app.Application
import app.knapp.exerciser.data.api.apiModule
import app.knapp.exerciser.data.repository.repositoryModule
import app.knapp.exerciser.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ExerciserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // todo: enable logger again once https://github.com/helpscout/beacon-android-sdk-sample/issues/170 is resolved
            // androidLogger()
            androidContext(this@ExerciserApplication)
            modules(listOf(
                apiModule,
                repositoryModule,
                uiModule
            ))
        }
    }

}