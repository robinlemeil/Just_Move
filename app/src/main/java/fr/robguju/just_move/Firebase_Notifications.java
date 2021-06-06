package fr.robguju.just_move;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Firebase_Notifications extends FirebaseMessagingService {

    private static final String CANAL = "MyCanal";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String msg = remoteMessage.getNotification().getBody();

        //clic sur la notification
        Intent intent = new Intent(getApplicationContext(),Page_connexion.class);
        PendingIntent pending = PendingIntent.getActivity(this,0,intent,0);

        //composition de la notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CANAL);
        builder.setContentTitle("Titre de la notif");
        builder.setContentText(msg);
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentIntent(pending);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channel_Id = getString(R.string.channel_id);
            String channel_title = getString(R.string.channel_title);
            String channel_text = getString(R.string.channel_text);
            NotificationChannel channel = new NotificationChannel(channel_Id,channel_title,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channel_text);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channel_Id);
        }

        manager.notify(1,builder.build());

    }
}
