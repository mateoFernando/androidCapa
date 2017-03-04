package gmd.plantilla.androidapp.domain.model;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jmauricio on 01/06/2016.
 */
public class User  extends RealmObject {

    private String CodigoUsuarioSesion;
    private String password;


    public String getCodigoUsuarioSesion() {
        return CodigoUsuarioSesion;
    }

    public void setCodigoUsuarioSesion(String codigoUsuarioSesion) {
        CodigoUsuarioSesion = codigoUsuarioSesion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
