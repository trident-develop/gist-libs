package libs.trident.gist.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitServices {
    @GET
    fun checkStatus(@Url url: String): Call<ResponseBody>
}