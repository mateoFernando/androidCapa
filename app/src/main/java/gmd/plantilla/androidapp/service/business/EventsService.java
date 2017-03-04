package gmd.plantilla.androidapp.service.business;

import android.content.Context;

public interface EventsService {

    public void LoadEventsLista(final Context context, Integer idUsuario, Integer idEje, Integer numPagina);

}
