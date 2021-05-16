package app.knapp.exerciser.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
example
[    {        "id": 1,        "name": "Move hip",        "cover_image_url": "https://drive.google.com/uc?export=download&id=1fbwzTPHJGT8T8-f8DB3LME2WLrwOVdJB",        "video_url": "https://drive.google.com/uc?export=download&id=19krvtD3TUP0r5Dy_u9n2Z1HNdVy4Q71M"    },    {        "id": 5,        "name": "Tabletop Arm-Leg Hold",        "cover_image_url": "https://drive.google.com/uc?export=download&id=1ONVXTY0Vm1cTVjTwpL5mTugEHDoT7Hf3",        "video_url": "https://drive.google.com/uc?export=download&id=1b1eSmx0JzxsXsY-D_jQ6o_kv9NXcKrol"    },    {        "id": 7,        "name": "Knee Plank",        "cover_image_url": "https://drive.google.com/uc?export=download&id=1EePbcRzfHDfqBoFM3kRp_ETPD1R0Z48L",        "video_url": "https://drive.google.com/uc?export=download&id=1EgB82ipUbD6tYWxBvMghHVEVI1CtbJL6"    },    {        "id": 11,        "name": "Side Plank",        "cover_image_url": "https://drive.google.com/uc?export=download&id=10u4GPMD39ATA4ONueWYSHMKpgd_yJ727",        "video_url": "https://drive.google.com/uc?export=download&id=16K2wToUqea6xlNGcqgjAYvRGLqi3175o"    },    {        "id": 19,        "name": "Reverse Crunch Hold",        "cover_image_url": "https://drive.google.com/uc?export=download&id=1I1LLrfRtymPXHSCmHz6AAkLs4HhH30WX",        "video_url": "https://drive.google.com/uc?export=download&id=1xPIaGyDKXvjViq5nLim6CHUiOZn-UDC6"    }]
 */

@Serializable
data class ExerciseApiResponse(
    val id: Int,
    val name: String,
    @SerialName("cover_image_url")
    val coverImageURL: String,
    @SerialName("video_url")
    val videoURL: String
)
