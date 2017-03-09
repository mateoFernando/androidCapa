package gmd.plantilla.androidapp.service.dao.impl;

import gmd.plantilla.androidapp.domain.model.Disc;
import gmd.plantilla.androidapp.domain.model.User;
import gmd.plantilla.androidapp.service.dao.DiscDAO;
import gmd.plantilla.androidapp.service.dao.UserDAO;
import io.realm.Realm;

/**
 * Created by jmauriciog on 07/06/2016.
 */
public class DiscDAOImpl implements DiscDAO{

    @Override
    public long insert(Disc model) {
        long id = 0 ;
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();

            realm.delete(Disc.class);
            Disc user = realm.createObject(Disc.class);

            user.setName(model.getName());
            user.setFavorite(model.getFavorite());
            user.setDate(model.getDate());
            user.setDistance(model.getDistance());
            user.setDistrict(model.getDistrict());
            user.setId(model.getId());
            user.setImage(model.getImage());
            user.setPlace(model.getPlace());
            user.setState(model.getState());


            id = 1;
            realm.commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            realm.cancelTransaction();
        }

        return id;
    }

    @Override
    public Disc getCurrentDisc() {
        // TODO Auto-generated method stub
        Disc user = null;
        Realm realm = Realm.getDefaultInstance();
        try{
            user = realm.where(Disc.class).findFirst();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

}
