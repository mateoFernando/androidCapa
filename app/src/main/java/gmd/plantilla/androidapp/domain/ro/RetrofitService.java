package gmd.plantilla.androidapp.domain.ro;

/**
 * Created by jmauriciog on 30/12/2016.
 * Interface Retrofit declare services
 */

import gmd.plantilla.androidapp.domain.ro.request.LoginRequest;
import gmd.plantilla.androidapp.domain.ro.request.ParametricRequest;
import gmd.plantilla.androidapp.domain.ro.request.TokenRequest;
import gmd.plantilla.androidapp.domain.ro.response.DiscResponse;
import gmd.plantilla.androidapp.domain.ro.response.EventResponse;
import gmd.plantilla.androidapp.domain.ro.response.LocalesResponse;
import gmd.plantilla.androidapp.domain.ro.response.LoginResponse;
import gmd.plantilla.androidapp.domain.ro.response.ParametricResponse;
import gmd.plantilla.androidapp.domain.ro.response.TokenResponse;
import gmd.plantilla.androidapp.util.Constants;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {

    @POST(Constants.SERVICES.LOGIN_URL)
    @Headers({
            "Content-Type: application/json",
            "application-id:  5DDBC5C8-8DC8-7032-FF90-CC659A9D4900",
            "secret-key:  AABAFF2B-0009-6554-FF87-429636EDF100",
            "application-type: REST"
    })
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST(Constants.SERVICES.GET_PARAMETRIC_URL)
    Call<ParametricResponse> parametric(@Body ParametricRequest request);

    @POST(Constants.SERVICES.UPDATE_TOKEN_URL)
    Call<TokenResponse> updateToken(@Body TokenRequest request);

    @GET(Constants.SERVICES.DISC_URL+"?")
    @Headers({
            "Content-Type: application/json",
            "application-id:  5DDBC5C8-8DC8-7032-FF90-CC659A9D4900",
            "secret-key:  AABAFF2B-0009-6554-FF87-429636EDF100",
            "application-type: REST"
    })
    Call<DiscResponse> getDiscs(@Query("pageSize") Integer pageSize,@Query("offset") Integer offset);

    @GET(Constants.SERVICES.LOCALES_URL)
    @Headers({
            "Content-Type: application/json",
            "application-id:  5DDBC5C8-8DC8-7032-FF90-CC659A9D4900",
            "secret-key:  AABAFF2B-0009-6554-FF87-429636EDF100",
            "application-type: REST"
    })
    Call<LocalesResponse> getLocales();


    @GET(Constants.SERVICES.EVENTS_URL+"?")
    @Headers({
            "Content-Type: application/json",
            "application-id:  5DDBC5C8-8DC8-7032-FF90-CC659A9D4900",
            "secret-key:  AABAFF2B-0009-6554-FF87-429636EDF100",
            "application-type: REST"
    })
    Call<EventResponse> getEvents(@Query("pageSize") Integer pageSize,@Query("offset") Integer offset,@Query("where") String id_disc);

}