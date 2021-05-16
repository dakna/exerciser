package app.knapp.exerciser.ui.main

data class ExerciseViewData(
    val id: Int,
    val name: String,
    val coverImageURL: String,
    val videoURL: String,
    val isFavorite: Boolean
)
