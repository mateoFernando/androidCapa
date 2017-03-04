package gmd.plantilla.androidapp.service.dao.impl;

import gmd.plantilla.androidapp.domain.model.User;
import gmd.plantilla.androidapp.service.dao.UserDAO;
import io.realm.Realm;

/**
 * Created by jmauriciog on 07/06/2016.
 */
public class UserDAOImpl implements UserDAO{

    @Override
    public long insert(User model) {
        long id = 0 ;
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();

            realm.delete(User.class);
            User user = realm.createObject(User.class);

            user.setCodigoUsuarioSesion(model.getCodigoUsuarioSesion());
            user.setPassword(model.getPassword());

            id = 1;
            realm.commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            realm.cancelTransaction();
        }

        return id;
    }

    @Override
    public User getCurrentUser() {
        // TODO Auto-generated method stub
        User user = null;
        Realm realm = Realm.getDefaultInstance();
        try{
            user = realm.where(User.class).findFirst();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void logout(){
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.delete(User.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
