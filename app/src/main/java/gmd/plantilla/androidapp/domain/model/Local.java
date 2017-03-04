package gmd.plantilla.androidapp.domain.model;

/**
 * Created by juanmauricio on 4/03/17.
 */

public class Local {

    private String Longitud;
    private String Latitud;
    private String Descripcion;

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
