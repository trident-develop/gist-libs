package libs.trident.gist.gistmanager

import com.google.gson.annotations.SerializedName

data class Gist(

    @SerializedName("switch") var switch : Boolean,
    @SerializedName("timezone") var timezone : String

)