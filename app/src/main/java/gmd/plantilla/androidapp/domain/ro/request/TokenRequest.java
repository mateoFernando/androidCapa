package gmd.plantilla.androidapp.domain.ro.request;

/**
 * Created by jmauriciog on 30/12/2016.
 */

public class TokenRequest {

    private String idUsuario;
    private String sisOperativo;
    private String token;

    public TokenRequest(String idUsuario, String sisOperativo, String token) {
        this.idUsuario = idUsuario;
        this.sisOperativo = sisOperativo;
        this.token = token;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSisOperativo() {
        return sisOperativo;
    }

    public void setSisOperativo(String sisOperativo) {
        this.sisOperativo = sisOperativo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
