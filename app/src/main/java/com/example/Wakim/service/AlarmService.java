package com.example.Wakim.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;



import org.jetbrains.annotations.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.Wakim.R;
import com.example.Wakim.activities.RingActivity;




import static com.example.Wakim.application.App.CHANNEL_ID;
import static com.example.Wakim.broadcastReceiver.AlarmBroadcastReceiver.TITLE;

/**
 * The service will run in the foreground and display a notification while playing a looping alarm sound
 * and a looping vibration pattern. When the user clicks on the notice, they will be sent to an activity where they will scan the QR code for snoozing.
 */
public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    /**
     * Takes and initializes services and manager for the media player and alarm. It also utilizes the default alarm tone of the device
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
                    .setLegacyStreamType(AudioManager.STREAM_ALARM)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build());
        }
        mediaPlayer.setLooping(true);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * This method builds a notification for the alarm, play a looping alarm sound, and a looping
     * vibration pattern. Whenever the user, clicks the notification, they will be redirected to the
     * Ring Activity wherein they are going to scan the code for dismissal of the alarm.
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, RingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        String alarmTitle = String.format("%s Alarm", intent.getStringExtra(TITLE));
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(alarmTitle)
                .setContentText("Umpisahi lang bala")
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setContentIntent(pendingIntent)
                .build();

        if(mediaPlayer.isPlaying()) {
            mediaPlayer.release();
        } else {
            mediaPlayer.start();
        }
        long[] pattern = {100, 200, 100, 200};
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            VibrationEffect vibe = VibrationEffect.createWaveform(pattern, 0);
            vibrator.vibrate(vibe);
        }
        else{
            vibrator.vibrate(pattern, -1);
        }

        startForeground(1, notification);

        return START_STICKY;
    }

    /**
     * Terminates the alarm sound and the vibration
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        vibrator.cancel();
    }

    /**
     * This method is used for binding MediaBrowserService
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}