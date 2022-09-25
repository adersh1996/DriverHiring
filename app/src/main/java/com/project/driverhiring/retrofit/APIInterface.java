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
                                @Part("phone")RequestBody phone,
                               @Part("email")RequestBody email,
                               @Part("password")RequestBody password,
                               @Part("address")RequestBody address,
                                @Part("district")RequestBody district,
                                @Part("state")RequestBody state,
                                @Part("experience")RequestBody experience,
                                @Part("languages")RequestBody languages,
                                @Part("blood")RequestBody bloodGroup,
                                @Part("device_token")RequestBody deviceToken,
                                @Part("hgmv")RequestBody hgmv,
                                @Part("long_distance")RequestBody longDrive,
                                @Part("route_pref")RequestBody routePref,
                                @Part("account_no")RequestBody accountNo,
                                @Part("ifsc")RequestBody iFsc,
                                @Part MultipartBody.Part licenceImage,
                                @Part MultipartBody.Part proImage);

    @GET("driver_login.php")
    Call<Root>DRIVER_LOGIN(@Query("phone")String phone,@Query("password")String password);

    @GET("user_login.php")
    Call<Root>USER_LOGIN(@Query("phone")String phone,@Query("password")String password);

    @GET("view_driver_list.php")
    Call<Root>DRIVER_LIST_CALL(@Query("s_lat")String userLat,@Query("s_long")String userLong,
                               @Query("d_lat")String destinationLat,@Query("d_long")String destinationLong,
                               @Query("hgmv")String vehicleType,@Query("route_pref")String routePref);
}
