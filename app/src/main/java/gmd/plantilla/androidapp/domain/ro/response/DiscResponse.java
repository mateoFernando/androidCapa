package gmd.plantilla.androidapp.domain.ro.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jmauriciog on 30/12/2016.
 */

public class DiscResponse {

    /*private User Data;

    public User getData() {
        return Data;
    }

    public void setData(User data) {
        Data = data;
    }*/



    //@SerializedName("name")
    private String name;

    @SerializedName("___class")
    private String type;

    @SerializedName("user-token")
    private String token;

    private String email;

    private String objectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
