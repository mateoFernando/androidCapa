package gmd.plantilla.androidapp.service.business.impl;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import gmd.plantilla.androidapp.domain.model.Disc;
import gmd.plantilla.androidapp.domain.ro.response.DiscResponse;
import gmd.plantilla.androidapp.service.business.DiscsService;
import gmd.plantilla.androidapp.util.Constants;
import gmd.plantilla.androidapp.view.AndroidApplication;
import pe.com.gmd.ao.innova.androidLib.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiscsImpl implements DiscsService {

    @Override
    public void LoadDiscsLista(Context context, Integer idUsuario, Integer idEje, Integer numPagina) {

        Call<DiscResponse> call = AndroidApplication.getInstance().getService().getDiscs(4,numPagina);

        call.enqueue(new Callback<DiscResponse>() {
            @Override
            public void onResponse(Call<DiscResponse> call, Response<DiscResponse> response) {
                LogUtil.i(response.toString());
                if (response.isSuccessful()){
                        List<Disc> discs= response.body().getResult();
                        EventBus.getDefault().post(discs);
                }

            }

            @Override
            public void onFailure(Call<DiscResponse> call, Throwable t) {
                LogUtil.e("error", t.toString());
                DiscResponse discResponse = new DiscResponse();
                EventBus.getDefault().post(discResponse);
            }
        });
    }
}
