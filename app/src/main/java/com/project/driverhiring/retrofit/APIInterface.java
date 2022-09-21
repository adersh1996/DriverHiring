package com.project.driverhiring.retrofit;

import com.project.driverhiring.model.Root;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {


    @Multipart
    @POST("user_reg.php")
    Call<Root> USERREGROOTCALL(@Part("name")RequestBody name,
                           @Part("email")RequestBody email,
                           @Part("phone")RequestBody phone,
                           @Part("password")RequestBody password,
                           @Part("address")RequestBody address,
                           @Part MultipartBody.Part licenceImage,
                            @Part MultipartBody.Part proImage);

    @Multipart
    @POST("driver_reg.php")
    Call<Root> DRIVEREGROOTCALL(@Part("name")RequestBody name,
                               @Part("email")RequestBody email,
                               @Part("phone")RequestBody phone,
                               @Part("password")RequestBody password,
                               @Part("address")RequestBody address,
                                @Part("district")RequestBody district,
                                @Part("state")RequestBody state,
                                @Part("vehicle")RequestBody vehicle,
                                @Part("experience")RequestBody experience,
                                @Part("working_hours")RequestBody workingHours,
                                @Part("languages")RequestBody languages,
                                @Part("blood")RequestBody bloodGroup,
                                @Part MultipartBody.Part licenceImage,
                                @Part MultipartBody.Part proImage,
                                @Part("preference")RequestBody preference);

    @GET("driver_login.php")
    Call<Root>DRIVER_LOGIN(@Query("phone")String phone,@Query("password")String password);

    @GET("user_login.php")
    Call<Root>USER_LOGIN(@Query("phone")String phone,@Query("password")String password);
}
