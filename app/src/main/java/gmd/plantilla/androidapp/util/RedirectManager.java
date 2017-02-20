package gmd.plantilla.androidapp.util;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Method;

/**
 * Created by jmauriciog on 01/06/2016.
 *
 */
public class RedirectManager {

    public static void returnToCallback(Context ctx, Object response,String callBackName){
        try {
            if(callBackName != null) {
                Class<?>[] parameters;
                parameters = new Class[1];
                parameters[0] = response.getClass();

                String className = ctx.getClass().getSuperclass().getSimpleName();
                Object classInstance = new Object();

                switch (className){
                    case "AppCompatActivity":
                        classInstance = (AppCompatActivity) ctx;
                        break;
                    case "FragmentActivity":
                        classInstance = (FragmentActivity) ctx;
                        break;
                    case "Activity":
                        classInstance = (Activity) ctx;
                        break;
                    case "Service":
                        classInstance = (Service) ctx;
                        break;
                    case "IntentService":
                        classInstance = (IntentService) ctx;
                        break;
                }

                Method method = classInstance.getClass().getMethod(callBackName, parameters);
                method.invoke(classInstance, response);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
