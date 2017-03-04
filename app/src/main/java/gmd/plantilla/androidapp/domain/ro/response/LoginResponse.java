package gmd.plantilla.androidapp.domain.ro.response;

import gmd.plantilla.androidapp.domain.model.User;

/**
 * Created by jmauriciog on 30/12/2016.
 */

public class LoginResponse extends BaseResponse {

    private String CodigoUsuarioSesion;
    private String Password;

    public LoginResponse(String codigoUsuarioSesion, String password) {
        CodigoUsuarioSesion = codigoUsuarioSesion;
        Password = password;
    }

    public LoginResponse(){

    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCodigoUsuarioSesion() {
        return CodigoUsuarioSesion;
    }

    public void setCodigoUsuarioSesion(String codigoUsuarioSesion) {
        CodigoUsuarioSesion = codigoUsuarioSesion;
    }

}
