package app.knapp.exerciser.ui.workout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkoutViewData(
    val exerciseId : Int,
    val exerciseName: String,
    val videoUrl: String,
    var isCompleted: Boolean
) : Parcelable