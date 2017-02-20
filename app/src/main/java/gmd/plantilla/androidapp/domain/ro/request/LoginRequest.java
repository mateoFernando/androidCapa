package gmd.plantilla.androidapp.domain.ro.request;

/**
 * Created by jmauriciog on 07/06/2016.
 */
public class LoginRequest {

    private String CodigoUsuarioSesion;
    private String Password;

    public LoginRequest(String codigoUsuarioSesion, String password) {
        CodigoUsuarioSesion = codigoUsuarioSesion;
        Password = password;
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
