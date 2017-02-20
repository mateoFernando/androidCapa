package gmd.plantilla.androidapp.service.dao;

import java.util.ArrayList;

import gmd.plantilla.androidapp.domain.model.Parametric;

/**
 * Created by jmauriciog on 02/06/2016.
 */
public interface ParametricDAO {

    public boolean isEmpty();

    public ArrayList<Parametric> getElements();

    public long insertAll(ArrayList<Parametric> models);

}
