package libs.trident.gist.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String, context: Context): Retrofit {


        // Get android user agent.
        val okHttp = OkHttpClient()
            .newBuilder()
            .addInterceptor(CustomInterceptor(System.getProperty("http.agent"), context))
            .followRedirects(false)
            .followSslRedirects(false)
            .build()
        //OkHttpClient okHttp = new OkHttpClient()
        //okHttp.interceptors().add(new UserAgentInterceptor(UA));

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()
        }
        return retrofit!!
    }
}