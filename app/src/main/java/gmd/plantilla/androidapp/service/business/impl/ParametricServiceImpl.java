package gmd.plantilla.androidapp.service.business.impl;

import android.content.Context;

import gmd.plantilla.androidapp.domain.ro.request.ParametricRequest;
import gmd.plantilla.androidapp.view.AndroidApplication;
import gmd.plantilla.androidapp.domain.ro.response.ParametricResponse;
import gmd.plantilla.androidapp.service.business.ParametricService;
import gmd.plantilla.androidapp.service.dao.ParametricDAO;
import gmd.plantilla.androidapp.service.dao.impl.ParametricDAOImpl;
import pe.com.gmd.ao.innova.androidLib.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jmauriciog on 02/06/2016.
 */
public class ParametricServiceImpl implements ParametricService{

    private ParametricDAO parametricDAO = new ParametricDAOImpl();

    @Override
    public boolean isEmpty() {
        return parametricDAO.isEmpty();
    }

    @Override
    public void updateParametricFromWebService(final Context ctx) {
        ParametricRequest parametricRequest = new ParametricRequest();
        Call<ParametricResponse> call = AndroidApplication.getInstance().getService().parametric(parametricRequest);
        call.enqueue(new Callback<ParametricResponse>() {
            @Override
            public void onResponse(Call<ParametricResponse> call, Response<ParametricResponse> response) {
                if(response.isSuccessful()){
                    ParametricResponse parametricResponse = response.body();
                    parametricDAO.insertAll(parametricResponse.getParametricList());
                    LogUtil.d("parametricResponse", "parametricResponse" + response.body().toString());
                }
                else{

                }
            }
            @Override
            public void onFailure(Call<ParametricResponse> call, Throwable t) {
                LogUtil.e("error", t.toString());
            }

        });
    }

    @Override
    public void getParametricFromWebService(final Context ctx, final String callback) {
        ParametricRequest parametricRequest = new ParametricRequest();
        Call<ParametricResponse> call = AndroidApplication.getInstance().getService().parametric(parametricRequest);
        call.enqueue(new Callback<ParametricResponse>() {
            @Override
            public void onResponse(Call<ParametricResponse> call, Response<ParametricResponse> response) {
                if(response.isSuccessful()){
                    ParametricResponse parametricResponse = response.body();
                    parametricDAO.insertAll(parametricResponse.getParametricList());
                    LogUtil.d("parametricResponse", "parametricResponse" + response.body().toString());

                   // RedirectManager.returnToCallback(ctx, parametricResponse, callback);
                }
                else{

                }
            }
            @Override
            public void onFailure(Call<ParametricResponse> call, Throwable t) {
                LogUtil.e("error", t.toString());
                ParametricResponse parametricResponse = new ParametricResponse();
                parametricResponse.setResultCode(0);
                parametricResponse.setMessage(t.toString());
              //  RedirectManager.returnToCallback(ctx, parametricResponse, callback);
            }
        });
    }

}
