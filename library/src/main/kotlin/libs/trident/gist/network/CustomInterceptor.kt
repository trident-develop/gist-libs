package libs.trident.gist.network

import android.content.Context
import android.net.Uri
import android.util.Log
import com.onesignal.OneSignal
import libs.trident.gist.Utils
import libs.trident.gist.storage.persistroom.model.Link
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.Throws

class CustomInterceptor(private val userAgent: String, var context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val requestWithUserAgent = originRequest.newBuilder()
            .header("User-Agent", userAgent)
            .build()

        Log.d("library", requestWithUserAgent.url().toString() + " - okhttp request with user agent")
        val response = chain.proceed(requestWithUserAgent)
        Log.d("library", response.headers().get("location") + " location header")

        val uri: Uri = Uri.parse(response.headers().get("location"))


        OneSignal.sendTag("key2", uri.getQueryParameter("signal"))

        //creating repo instance
        Utils.createRepoInstance(context.applicationContext).linkDao.addLink(
            Link(
                1,
                response.headers().get("location")
            )
        )
        Log.d("library", chain.request().url().toString() + " - okhttp url redirect")


        return response
    }
}