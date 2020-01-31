package callplan.prm.kalbe.kalbecallplanmobile.networkDAO;


import callplan.prm.kalbe.kalbecallplanmobile.model.network.ResponseGetToken;
import callplan.prm.kalbe.kalbecallplanmobile.model.network.postData.PostData;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TokenService {

    // @POST("knglobal/api/globalapi/PostData")
    // @POST("LoginV2_J")
    @POST("api/globalapi/PostData")
    @Headers({"Content-Type: application/json"})
    Observable<PostData> samplePost(@Body PostData postData);

    @FormUrlEncoded
    @POST("token")
    Observable<ResponseGetToken> getToken(
            @Field("grant_type") String grant_type,
            @Field("username") String username,
            @Field("password") String password
    );

}
