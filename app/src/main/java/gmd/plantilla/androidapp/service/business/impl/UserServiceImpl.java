package gmd.plantilla.androidapp.service.business.impl;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import gmd.plantilla.androidapp.domain.model.User;
import gmd.plantilla.androidapp.domain.ro.request.LoginRequest;
import gmd.plantilla.androidapp.domain.ro.response.LoginResponse;
import gmd.plantilla.androidapp.service.business.UserService;
import gmd.plantilla.androidapp.service.dao.UserDAO;
import gmd.plantilla.androidapp.service.dao.impl.UserDAOImpl;
import gmd.plantilla.androidapp.view.AndroidApplication;

import pe.com.gmd.ao.innova.androidLib.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jmauriciog on 01/06/2016.
 */
public class UserServiceImpl implements UserService {

    UserDAO userDao = new UserDAOImpl();

    @Override
    public void login(final Context ctx, String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username,password);
        Call<LoginResponse> call = AndroidApplication.getInstance().getService().login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    LogUtil.d("LoginResponse", "LoginResponse" + response.body().toString());
                    User user = new User();
                    user.setPassword(loginResponse.getPassword());
                    user.setCodigoUsuarioSesion(loginResponse.getCodigoUsuarioSesion());
                    userDao.insert(user);
                    EventBus.getDefault().post(loginResponse);
                }
                else{

                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("error", t.toString());
                LoginResponse loginResponse=new LoginResponse();

                EventBus.getDefault().post(loginResponse);
            }
        });
    }

    @Override
    public User getCurrentUser(){
        return userDao.getCurrentUser();
    }

    @Override
    public void logout() {
        userDao.logout();
    }


}
