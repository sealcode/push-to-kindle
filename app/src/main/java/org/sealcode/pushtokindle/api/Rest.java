package org.sealcode.pushtokindle.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Maciej on 11.12.2016.
 */

interface Rest {

    @FormUrlEncoded
    @POST("send.php")
    Call<ResponseBody> sendWebsite(@Query("context") String context, @Query("url") String url,
                                   @Field("from") String from, @Field("title") String title,
                                   @Field("email") String email, @Field("domain") String domain,
                                   @Field("save") String save);

    @GET("send.php")
    Call<ResponseBody> checkConversion(@Query("context") String context, @Query("url") String url);
}
