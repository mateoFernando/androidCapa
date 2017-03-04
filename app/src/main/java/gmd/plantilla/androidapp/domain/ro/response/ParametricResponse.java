package gmd.plantilla.androidapp.domain.ro.response;

import java.util.ArrayList;

import gmd.plantilla.androidapp.domain.model.Parametric;

/**
 * Created by jmauriciog on 03/06/2016.
 */
public class ParametricResponse extends BaseResponse {

    ArrayList<Parametric> parametricList;

    public ArrayList<Parametric> getParametricList() {
        return parametricList;
    }

    public void setParametricList(ArrayList<Parametric> parametricList) {
        this.parametricList = parametricList;
    }


}
