package libs.trident.gist.gistmanager

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface Model {

    @GET
    fun getRemoteData(@Url url: String): Observable<Gist>

}