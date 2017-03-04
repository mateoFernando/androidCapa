package gmd.plantilla.androidapp.domain.ro.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by glarab on 03/06/2016.
 */

public class BaseResponse_Backendless {


    private static final int SUCCESS=0;
    private int code;
    private String message;

    @SerializedName("IsSuccess")
    private int resultCode;

    /*@SerializedName("Exception")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }*/

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }


    public boolean isSuccess()
    {
        if(this.code==SUCCESS)return true;
        return false;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
