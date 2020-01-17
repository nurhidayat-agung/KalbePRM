package callplan.prm.kalbe.kalbecallplanmobile.networkDAO;


import callplan.prm.kalbe.kalbecallplanmobile.model.network.PostData;
import callplan.prm.kalbe.kalbecallplanmobile.model.network.ResponseGetToken;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TokenService {

    // @POST("knglobal/api/globalapi/PostData")
    // @POST("LoginV2_J")
    @POST("api/globalapi/PostData")
    @Headers({"Content-Type: application/json"})
    Observable<ResponseGetToken> getResultToken(@Body PostData postData);



}
