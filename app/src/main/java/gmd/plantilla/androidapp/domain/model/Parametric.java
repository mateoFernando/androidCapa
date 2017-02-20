package gmd.plantilla.androidapp.domain.model;

import io.realm.RealmObject;
import pe.com.gmd.ao.innova.androidLib.RedirectManager;

/**
 * Created by jmauriciog on 02/06/2016.
 */
public class Parametric  extends RealmObject {

    private String key;
    private String value;

    public Parametric(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
