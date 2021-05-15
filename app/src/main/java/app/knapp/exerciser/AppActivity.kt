package app.knapp.exerciser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.knapp.exerciser.ui.main.MainFragment

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)
    }
}