package gmd.plantilla.androidapp.remote;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.Map;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.view.AndroidApplication;
import gmd.plantilla.androidapp.view.activity.MainActivity;

/**
 * Created by jmauriciog on 15/12/2015.
 */
public class FcmMessageHandler extends FirebaseMessagingService {

    public static final int MESSAGE_NOTIFICATION_ID = 1;

    @Override
    public void onMessageReceived(RemoteMessage message) {

        Map data = message.getData();

        if(!data.isEmpty())
            createNotification(data.toString());
    }

    private void createNotification(String body) {
        Context context = getBaseContext();

        int notificationCount = AndroidApplication.getInstance().incrementAndGetNotificationCount();

        ArrayList<String> notificationList = AndroidApplication.getInstance().getNotificationList();
        notificationList.add(body);

        /* Big View Specific Configuration */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(getResources().getString(R.string.app_name));

        for(int i=0; i < notificationList.size(); i++)
            inboxStyle.addLine(notificationList.get(i));

        if(notificationCount > 1)
            inboxStyle.setSummaryText(notificationCount+ " nuevas notificaciones.");
        else
            inboxStyle.setSummaryText(notificationCount+" nueva notificacion.");
        /* Big View Specific Configuration */

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setNumber(notificationCount)
                .setStyle(inboxStyle)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

        long[] pattern = { 500, 500, 500 };
        mBuilder.setVibrate(pattern);
        mBuilder.setLights(0xff00ff00, 300, 1000);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        if(notificationCount > 1)
            mBuilder.setContentText(notificationCount+ " nuevas notificaciones.");
        else
            mBuilder.setContentText(body);

        // redirect to activity
        Intent intentActivity = new Intent(this, MainActivity.class);
        intentActivity.putExtra("gcm_message", 1);
        intentActivity.setAction(Long.toString(System.currentTimeMillis()));
        intentActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intentActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);

        AndroidApplication.getInstance().getNotificationManager().notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());

    }

}
