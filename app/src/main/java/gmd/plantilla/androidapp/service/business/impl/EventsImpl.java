package gmd.plantilla.androidapp.service.business.impl;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import gmd.plantilla.androidapp.domain.model.Disc;
import gmd.plantilla.androidapp.domain.model.Event;
import gmd.plantilla.androidapp.domain.ro.response.DiscResponse;
import gmd.plantilla.androidapp.domain.ro.response.EventResponse;
import gmd.plantilla.androidapp.service.business.DiscsService;
import gmd.plantilla.androidapp.service.business.EventsService;
import gmd.plantilla.androidapp.view.AndroidApplication;
import pe.com.gmd.ao.innova.androidLib.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventsImpl implements EventsService {

    @Override
    public void LoadEventsLista(Context context, Integer idUsuario, Integer idEje, Integer numPagina) {
        String id_disc=null;
        if(idUsuario!=null)
            id_disc="id_disc="+idUsuario;

        Call<EventResponse> call = AndroidApplication.getInstance().getService().getEvents(2,numPagina,id_disc);

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                LogUtil.i(response.toString());
                if (response.isSuccessful()){
                        List<Event> discs= response.body().getResult();
                        EventBus.getDefault().post(discs);
                }

            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                LogUtil.e("error", t.toString());
                EventResponse discResponse = new EventResponse();
                EventBus.getDefault().post(discResponse);
            }
        });
    }
}
