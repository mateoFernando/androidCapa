package gmd.plantilla.androidapp.service.dao.impl;

import java.util.ArrayList;

import gmd.plantilla.androidapp.domain.model.Parametric;
import gmd.plantilla.androidapp.service.dao.ParametricDAO;
import io.realm.Realm;

/**
 * Created by jmauriciog on 07/06/2016.
 */
public class ParametricDAOImpl implements ParametricDAO{

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        ArrayList<Parametric> parametricList = getElements();
        if (parametricList != null && parametricList.size() != 0)
            return false;
        else
            return true;
    }

    @Override
    public ArrayList<Parametric> getElements() {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<Parametric> parametricList = new ArrayList(realm.where(Parametric.class).findAll());
        return parametricList;
    }

    @Override
    public long insertAll(ArrayList<Parametric> models) {

        long insertedRows = 0 ;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.delete(Parametric.class);
            realm.insert(models);
            insertedRows = 1;
            realm.commitTransaction();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            realm.cancelTransaction();
        }

        return insertedRows;
    }
}
