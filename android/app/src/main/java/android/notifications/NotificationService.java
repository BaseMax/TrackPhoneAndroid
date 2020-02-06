package android.notifications;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;

import java.io.ByteArrayOutputStream;

public class NotificationService extends NotificationListenerService {

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        try {
            if(sbn == null || sbn.getNotification() == null) {
                return;
            }
            String pack = sbn.getPackageName();
            String ticker ="";
            if(sbn.getNotification().tickerText !=null) {
                ticker = sbn.getNotification().tickerText.toString();
            }
            Bundle extras = sbn.getNotification().extras;
            String title = "none";
            try {
                title=extras.getString("android.title").toString();
            }
            catch (Exception e) { }
            String text = "none";
            try {
//                text=extras.getCharSequence("android.text").toString();
                text=extras.getString("android.text").toString();
            }
            catch (Exception e) { }
            int id1 = extras.getInt(Notification.EXTRA_SMALL_ICON);
            Bitmap id = sbn.getNotification().largeIcon;

            Log.i("Package",pack);
            Log.i("Ticker",ticker);
            Log.i("Title",title);
            Log.i("Text",text);

            Intent msgrcv = new Intent("Msg");
            msgrcv.putExtra("package", pack);
            msgrcv.putExtra("ticker", ticker);
            msgrcv.putExtra("title", title);
            msgrcv.putExtra("text", text);
            if(id != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                id.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                msgrcv.putExtra("icon",byteArray);
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
        }
        catch (Exception ex) {}
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");

    }
}
