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
    public static int PLAY_SERVICES_TIMEOUT = 5000;

    public static int ASYNC_REQUEST = 1;
    public static int SYNC_REQUEST = 0;

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

        public static String URL_BASE_CERTIFICACION= "http://http://gisem.osinergmin.gob.pe/servgis/json/";
        public static String URL_BASE_PRODUCCION= "http://http://gisem.osinergmin.gob.pe/servgis/json/";
        public static String URL_BASE_USADA=URL_BASE_PRODUCCION;

        public static String GET_COORDENADAS_URL = URL_BASE_USADA + "buffer"; // parametros: coor X, coor Y, distancia
        public  static String URL_PRUEBA="http://gisem.osinergmin.gob.pe/servgis/json/buffer?x=-77.058&y=-12.0535&distance=50";

        public static final String GET_PARAMETRIC_URL = "parametric";
        public static final String UPDATE_TOKEN_URL = "token";
        public static final String LOGIN_URL = "login";
    }

    public static class VALUES {
        public static final int IMG_GIF = R.drawable.ic_arrow_back;//IMAGEN A MOSTAR
        public static float ZOOM_MAPA_DEFAULT = 14;
        public static double LATITUDE_POSITION_DEFAULT = -12.096701;
        public static double LONGITUDE_POSITION_DEFAULT = -77.058767;
    }

    // deben tener final ya que se ejecutará en runtime
    public static class PERMISSIONS{
        public static final int ACCESS_FINE_LOCATION = 1;
    }

    public static class PREFERENCES{

    }
}
