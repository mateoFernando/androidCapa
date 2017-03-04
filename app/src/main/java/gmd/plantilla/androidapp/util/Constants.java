package gmd.plantilla.androidapp.util;

import java.util.ArrayList;
import java.util.List;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Slide;

/**
 * Created by jmauriciog on 02/06/2016.
 */
public class Constants {

    public static final int DEFAULT_SOCKET_TIMEOUT =1 ;
    public static int SPLASH_DELAY = 4500;

    public static int APP_TIMEOUT = 5000;

    public static int SUCCESS_REQUEST = 1;
    public static int ERROR_REQUEST = 0;


    public static List<Slide> GETSLIDE() {
        ArrayList<Slide> items = new ArrayList<>();
        /**
         * Slide(@titulo,@descripcion,@ImagenPagedrawable)
         */
        items.add(new Slide(R.mipmap.group));
        items.add(new Slide(R.mipmap.group_3));
        items.add(new Slide(R.mipmap.group9));
        items.add(new Slide(R.mipmap.group_5));

        return items;
    }

    public static class SERVICES{

        public static String URL_BASE_USADA="http://api.backendless.com/v1/data/";

        public static final String GET_PARAMETRIC_URL = "parametric";
        public static final String UPDATE_TOKEN_URL = "token";
        public static final String LOGIN_URL = "login";
        public static final String DISC_URL = "Discs";
        public static final String EVENTS_URL = "Events";
        public static final String LOCALES_URL = "Locales";

    }

    public static class VALUES {
        public static float ZOOM_MAPA_DEFAULT = 14;
        public static double LATITUDE_POSITION_DEFAULT = -12.096701;
        public static double LONGITUDE_POSITION_DEFAULT = -77.058767;
    }

    // deben tener final ya que se ejecutará en runtime
    public static class PERMISSIONS{
        public static final int ACCESS_FINE_LOCATION = 1;
    }

}
