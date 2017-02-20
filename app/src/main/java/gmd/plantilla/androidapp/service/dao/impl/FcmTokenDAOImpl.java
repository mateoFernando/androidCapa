package gmd.plantilla.androidapp.service.dao.impl;

import gmd.plantilla.androidapp.domain.model.FcmToken;
import gmd.plantilla.androidapp.service.dao.FcmTokenDAO;
import io.realm.Realm;

/**
 * Created by jmauriciog on 07/06/2016.
 */
public class FcmTokenDAOImpl implements FcmTokenDAO {

    @Override
    public FcmToken getDevice() {
        FcmToken device = null;
        Realm realm = Realm.getDefaultInstance();
        try{
            device = realm.where(FcmToken.class).findFirst();
        }catch (Exception e){
            e.printStackTrace();
        }

        return device;
    }

    @Override
    public void updateTokenToExpired(){
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();
            FcmToken device = realm.where(FcmToken.class).findFirst();
            device.setState(0);
            realm.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
            realm.cancelTransaction();
        }
    }

    @Override
    public long insert(FcmToken model) {
        Realm realm = Realm.getDefaultInstance();
        long id = 0 ;
        try{
            realm.beginTransaction();

            realm.delete(FcmToken.class);
            FcmToken device = realm.createObject(FcmToken.class);
            device.setGcmId(model.getGcmId());
            device.setState(model.getState());

            id = 1;
            realm.commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            realm.cancelTransaction();
        }

        return id;
    }

}
