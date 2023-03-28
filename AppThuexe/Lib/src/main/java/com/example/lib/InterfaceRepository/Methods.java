package com.example.lib.InterfaceRepository;

import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXECallBackModel;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEInsertUpdateDeleteModel;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEModel;
import com.example.lib.Model.TK.TKCallBackModel;
import com.example.lib.Model.TK.TKInsertUpdateDeleteModel;
import com.example.lib.Model.TK.TKModel;
import com.example.lib.Model.XETHUE.XETHUECallBackModel;
import com.example.lib.Model.XETHUE.XETHUEInsertUpdateDeleteModel;
import com.example.lib.Model.XETHUE.XETHUEModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface Methods {
    ////////////// tai khoan
    @GET("api/TK/get-tk")
    Call<TKModel> GetTK();

    @POST("api/TK/insert-tk")
    Call<TKCallBackModel> insertTK(@Body TKInsertUpdateDeleteModel tkInsertUpdateDeleteModel);

    @PATCH("api/TK/update-tk")
    Call<TKCallBackModel> updateTK(@Body TKInsertUpdateDeleteModel tkInsertUpdateDeleteModel);

    @DELETE("api/TK/delete-tk")
    Call<TKCallBackModel> deleteTK(@Body TKInsertUpdateDeleteModel tkInsertUpdateDeleteModel);

    /////////////xe
    @GET("api/XETHUE/get-xethue")
    Call<XETHUEModel> GetXETHUE();

    @POST("api/XETHUE/insert-xethue")
    Call<XETHUECallBackModel> insertXETHUE(@Body XETHUEInsertUpdateDeleteModel xethueInsertUpdateDeleteModel);
    @PATCH("api/XETHUE/update-xethue")
    Call<XETHUECallBackModel> updateXETHUE(@Body XETHUEInsertUpdateDeleteModel xethueInsertUpdateDeleteModel);
    @DELETE("api/XETHUE/delete-xethue")
    Call<XETHUECallBackModel> deleteXETHUE(@Body XETHUEInsertUpdateDeleteModel xethueInsertUpdateDeleteModel);

    ///////////// phieu thue xe PHIEUTHUEXE
    @GET("api/PHIEUTHUEXE/get-phieuthuexe")
    Call<PHIEUTHUEXEModel> GetPHIEUTHUEXE();

    @POST("api/PHIEUTHUEXE/insert-phieuthuexe")
    Call<PHIEUTHUEXECallBackModel> insertPHIEUTHUEXE(@Body PHIEUTHUEXEInsertUpdateDeleteModel phieuthuexeInsertUpdateDeleteModel);

    @PATCH("api/PHIEUTHUEXE/update-phieuthuexe")
    Call<PHIEUTHUEXECallBackModel> updatePHIEUTHUEXE(@Body PHIEUTHUEXEInsertUpdateDeleteModel phieuthuexeInsertUpdateDeleteModel);

    @DELETE("api/PHIEUTHUEXE/delete-phieuthuexe")
    Call<PHIEUTHUEXECallBackModel> deletePHIEUTHUEXE(@Body PHIEUTHUEXEInsertUpdateDeleteModel phieuthuexeInsertUpdateDeleteModel);
}
