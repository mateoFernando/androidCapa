package gmd.plantilla.androidapp.view;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bumptech.glide.request.target.ViewTarget;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.ro.RetrofitService;
import gmd.plantilla.androidapp.util.Constants;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import pe.com.gmd.ao.innova.androidLib.LogUtils;
import pe.com.gmd.ao.innova.androidLib.Utils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jmauriciog on 01/06/16.
 * Clase principal de la app
 */
public class AndroidApplication extends Application{

    private static AndroidApplication sApplication;
    private Retrofit retrofit;

    private int notificationsCount = 0;
    private ArrayList<String> notificationList = new ArrayList<>();
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        sApplication = this;
        ViewTarget.setTagId(R.id.glide_tag);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
        Utils.init(sApplication);
        LogUtils.getBuilder().setTag("MIAPP").setLog2FileSwitch(true).create();
    }

    public static AndroidApplication getInstance() {
        return sApplication;
    }

    /** Retrofit Global methods **/
    public RetrofitService getService(){
        if(retrofit == null){
            Gson gson = new GsonBuilder()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SERVICES.URL_BASE_USADA)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getRequestHeader())
                    .build();
        }

        return retrofit.create(RetrofitService.class);

    }

    private OkHttpClient getRequestHeader() {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.newBuilder().readTimeout(Constants.APP_TIMEOUT, TimeUnit.SECONDS);
        httpClient.newBuilder().connectTimeout(Constants.APP_TIMEOUT, TimeUnit.SECONDS);
        return httpClient;
    }
    /** Retrofit Global methods **/


    /** GCM Global Methods **/
    public int incrementAndGetNotificationCount(){
        notificationsCount++;
        return notificationsCount;
    }

    public ArrayList<String> getNotificationList() {
        return notificationList;
    }

    public void clearNotifications(){
        notificationsCount = 0;
        getNotificationManager().cancelAll();
        notificationList.clear();
    }

    public NotificationManager getNotificationManager() {
        if(notificationManager == null)
            notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        return notificationManager;
    }
    /** GCM Global Methods **/

}
