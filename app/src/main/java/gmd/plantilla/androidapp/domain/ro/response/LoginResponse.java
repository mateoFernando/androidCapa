package gmd.plantilla.androidapp.domain.ro.response;

import gmd.plantilla.androidapp.domain.model.User;

/**
 * Created by jmauriciog on 30/12/2016.
 */

public class LoginResponse {

    private User Data;

    public User getData() {
        return Data;
    }

    public void setData(User data) {
        Data = data;
    }

}
