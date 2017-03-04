package gmd.plantilla.androidapp.remote;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import gmd.plantilla.androidapp.domain.model.FcmToken;
import gmd.plantilla.androidapp.domain.model.User;
import gmd.plantilla.androidapp.service.business.FcmTokenService;
import gmd.plantilla.androidapp.service.business.UserService;
import gmd.plantilla.androidapp.service.business.impl.FcmTokenServiceImpl;
import gmd.plantilla.androidapp.service.business.impl.UserServiceImpl;

/**
 * Created by jmauriciog on 15/12/2015.
 */
public class FcmIDListenerService extends FirebaseInstanceIdService {

    private FcmTokenService deviceService = new FcmTokenServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN GCM",refreshedToken);
        deviceService.setDevice(new FcmToken(refreshedToken,0));

        User user = userService.getCurrentUser();
        if(user != null){
            deviceService.sendRegistrationToServer(refreshedToken,user.getCodigoUsuarioSesion());
        }

    }
}