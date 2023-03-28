package com.example.lib;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    private static String Base_Url = "http://ttuan9988-001-site1.ctempurl.com";

    public static String getBase_Url() {
        return Base_Url;
    }

    public static void setBase_Url(String base_Url) {
        Base_Url = base_Url;
    }

    public static Retrofit getRetrofit(){
        if( retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
