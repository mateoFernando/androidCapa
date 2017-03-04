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

        /*UserService userService=new UserServiceImpl();
        User usuario = userService.getCurrentUser();*/

        Call<DiscResponse> call;
        Log.i("BandejaBeneficio", Constants.SERVICES.URL_BASE_USADA+"/"+Constants.SERVICES.DISC_URL);

        call = AndroidApplication.getInstance().getService().getDiscs();

        Log.i("CargarBeneficioLista",call.request().url().toString());

        call.enqueue(new Callback<DiscResponse>() {
            @Override
            public void onResponse(Call<DiscResponse> call, Response<DiscResponse> response) {

                LogUtil.i(response.toString());
                if (response.code() == 200){
                        List<Disc> beneficioListas= response.body().getResult();

                        EventBus.getDefault().post(beneficioListas);
                }

            }

            @Override
            public void onFailure(Call<DiscResponse> call, Throwable t) {
                LogUtil.e("error", t.toString());
                DiscResponse beneficioListaResponse = new DiscResponse();
                EventBus.getDefault().post(beneficioListaResponse);
            }
        });
    }
}
