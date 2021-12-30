package libs.trident.gist.network

import libs.trident.gist.constants.Constants

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL, Constants.appsContext).create(RetrofitServices::class.java)
}