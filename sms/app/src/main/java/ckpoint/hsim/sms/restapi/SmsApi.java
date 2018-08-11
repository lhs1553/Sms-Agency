package ckpoint.hsim.sms.restapi;



import ckpoint.hsim.sms.fcm.FcmToken;
import ckpoint.hsim.sms.fcm.SmsMsg;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SmsApi {

    @POST("/api/regist/token")
    Call<FcmToken> registToken(@Body FcmToken fcmToken);

    @GET("/api/send/callback/{id}")
    Call<Void> smsSendCallback(@Path("id") Long id);
}
