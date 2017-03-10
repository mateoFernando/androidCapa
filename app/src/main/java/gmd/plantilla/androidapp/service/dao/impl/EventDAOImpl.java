package gmd.plantilla.androidapp.service.dao.impl;

import gmd.plantilla.androidapp.domain.model.Event;
import gmd.plantilla.androidapp.service.dao.EventDAO;
import io.realm.Realm;

/**
 * Created by jmauriciog on 07/06/2016.
 */
public class EventDAOImpl implements EventDAO{

    @Override
    public long insert(Event model) {
        long id = 0 ;
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();

            realm.delete(Event.class);
            Event user = realm.createObject(Event.class);

            user.setDate(model.getDate());
            user.setType(model.getType());
            user.setDistrict(model.getDistrict());
            user.setName(model.getName());
            user.setDescription(model.getDescription());
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
    public Event getCurrentEvent() {
        // TODO Auto-generated method stub
        Event user = null;
        Realm realm = Realm.getDefaultInstance();
        try{
            user = realm.where(Event.class).findFirst();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

}
