package gmd.plantilla.androidapp.service.business.impl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import gmd.plantilla.androidapp.domain.model.Disc;
import gmd.plantilla.androidapp.domain.model.Local;
import gmd.plantilla.androidapp.domain.ro.response.DiscResponse;
import gmd.plantilla.androidapp.domain.ro.response.LocalesResponse;
import gmd.plantilla.androidapp.domain.ro.response.LoginResponse;
import gmd.plantilla.androidapp.service.business.LocalesService;
import gmd.plantilla.androidapp.view.AndroidApplication;
import pe.com.gmd.ao.innova.androidLib.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by juanmauricio on 4/03/17.
 */

public class LocalesServiceImpl implements LocalesService {

    @Override
    public void loadlocales() {
        Call<LocalesResponse> call = AndroidApplication.getInstance().getService().getLocales();

        call.enqueue(new Callback<LocalesResponse>() {
            @Override
            public void onResponse(Call<LocalesResponse> call, Response<LocalesResponse> response) {
                LogUtil.i(response.toString());
                if (response.isSuccessful()){
                    LocalesResponse localesResponse = response.body();
                    EventBus.getDefault().post(localesResponse);
                }

            }

            @Override
            public void onFailure(Call<LocalesResponse> call, Throwable t) {
                LogUtil.e("error", t.toString());
                LocalesResponse localesResponse = new LocalesResponse();
                EventBus.getDefault().post(localesResponse);
            }
        });

    }
}
