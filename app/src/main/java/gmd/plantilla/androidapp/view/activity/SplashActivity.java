package gmd.plantilla.androidapp.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.FcmToken;
import gmd.plantilla.androidapp.domain.model.User;
import gmd.plantilla.androidapp.domain.ro.response.ParametricResponse;
import gmd.plantilla.androidapp.service.business.FcmTokenService;
import gmd.plantilla.androidapp.service.business.ParametricService;
import gmd.plantilla.androidapp.service.business.UserService;
import gmd.plantilla.androidapp.service.business.impl.FcmTokenServiceImpl;
import gmd.plantilla.androidapp.service.business.impl.ParametricServiceImpl;
import gmd.plantilla.androidapp.service.business.impl.UserServiceImpl;
import gmd.plantilla.androidapp.util.AppPreferences;
import gmd.plantilla.androidapp.util.Constants;
import pe.com.gmd.ao.innova.androidLib.LogUtil;

public class SplashActivity extends AppCompatActivity {

    private TimerTask splash_task_parametric;
    private TimerTask splash_task;
    private Timer timer;
    private Context ctx;
    private ProgressDialog dialog;

    private FcmTokenService deviceService = new FcmTokenServiceImpl();
    private UserService userService = new UserServiceImpl();
    private ParametricService parametricService = new ParametricServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        timer = new Timer("SplashTimer",true);
        ctx = this;
        /** En caso se configure data maestra y deba descargarse al inicio **/
        splash_task_parametric = new TimerTask() {
            @Override
            public void run() {
                if(parametricService.isEmpty()){ // first time
                    ((AppCompatActivity)ctx).runOnUiThread(new Runnable() {
                        public void run() {
                            dialog = ProgressDialog.show(ctx, getResources().getString(R.string.app_name),
                                    "Estamos configurando la aplicación", true);

                        }
                    });
                    getParametricData();
                }
                else{
                    updateParametricData();
                    checkGCM();
                    goToNextActivity();
                }
            }
        };

        /** En caso sólo sea necesario actualizar token de notificación **/
        splash_task = new TimerTask() {
            @Override
            public void run() {
                checkGCM();
                goToNextActivity();
            }
        };

        timer.schedule(splash_task, Constants.SPLASH_DELAY);
    }

    @Override
    public void onBackPressed() {
        // block back press button
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(splash_task != null)
            splash_task.cancel();
        if(splash_task_parametric != null)
            splash_task_parametric.cancel();

    }

    private void getParametricData(){
        parametricService.getParametricFromWebService(ctx,"parametricCallback");
    }

    private void updateParametricData(){
        parametricService.updateParametricFromWebService(ctx);
    }

    private void checkGCM(){
        FcmToken device = deviceService.getDevice();
        User user = userService.getCurrentUser();
        if(device != null && device.getState() == 0 && user != null) // token no enviado y usuario en sesión
            deviceService.sendRegistrationToServer(device.getGcmId(),user.getCodigoUsuarioSesion());
    }




    /** Callback Methods **/
    public void parametricCallback(ParametricResponse parametricResponse){

        if(dialog!=null)
            dialog.dismiss();

        if(parametricResponse.getResultCode() == Constants.SUCCESS_REQUEST){
            goToNextActivity();
        }
        else{
            LogUtil.d("Intente nuevamente");

        }
    }
    /** Callback Methods **/




    /** Redirection Methods **/
    private void goToNextActivity(){
        if(userService.getCurrentUser() != null)
            goToMain();
        else{
            if(AppPreferences.getInstance(ctx).isOnBordingVisto())
                goToLogin();
            else
                goToSlide();
        }

    }
    private void goToLogin(){
        Intent i = new Intent(SplashActivity.this , LoginActivity.class);
        startActivity(i);
        finish();
    }
    private void goToSlide(){
        Intent i = new Intent(SplashActivity.this , SlideActivity.class);
        startActivity(i);
        finish();
    }

    private void goToMain(){
        Intent i = new Intent(SplashActivity.this , PrincipalActivity.class);
        startActivity(i);
        finish();
    }

    /** Redirection Methods **/

}
