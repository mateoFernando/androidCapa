package gmd.plantilla.androidapp.service.dao;

import java.util.ArrayList;

import gmd.plantilla.androidapp.domain.model.User;

/**
 * Created by jmauriciog on 01/06/2016.
 */
public interface UserDAO {

    public long insert(User model);

    public User getCurrentUser();

    public void logout();
}
