package gmd.plantilla.androidapp.service.business;

import android.content.Context;

import java.util.ArrayList;

import gmd.plantilla.androidapp.domain.model.User;

/**
 * Created by jmauriciog on 01/06/2016.
 */
public interface UserService {

    public void login(final Context ctx, String username, String password);

    public User getCurrentUser();
    public void logout();

}
