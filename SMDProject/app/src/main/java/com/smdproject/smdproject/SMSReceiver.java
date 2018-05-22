package com.smdproject.smdproject;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SMSReceiver extends BroadcastReceiver {
    private final String DEBUG_TAG = getClass().getSimpleName().toString();
    private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private Context mContext;
    private Intent mIntent;

    // Retrieve SMS
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;

        String action = intent.getAction();

        if(action.equals(ACTION_SMS_RECEIVED)){

            String address, str = "";

            SmsMessage[] msgs = getMessagesFromIntent(mIntent);
            if (msgs != null) {
                for (int i = 0; i < msgs.length; i++) {
                    address = msgs[i].getOriginatingAddress();
                    str += msgs[i].getMessageBody().toString();
                }
            }

            if(str.startsWith("@squadApp@")){

                str=str.substring(10);

                String[] splits=str.split("\n",2);

                if(splits.length<2)return;

                String header=splits[0];


                String[] headers=header.split("@");

                if(headers.length<5)return;

                String name=headers[0];
                String groupname=headers[1];
                String groupid=headers[2];
                String userid=headers[3];
                String date=headers[4];

                String message=splits[1];



                Date d=null;
                try {
                    d = new SimpleDateFormat("yyyyMMdd_HHmmss").parse(date);
                }catch(ParseException e){e.printStackTrace();}

                if(d==null)d=new Date();

                database.Message mo=new database.Message();
                mo.setGid(groupid);
                mo.setText(message);
                mo.setStamp(d);
                mo.setSenderid(userid);

                new MessageSaveAsyncTask(context).execute(mo);


                // Create an explicit intent for an Activity in your app
                Intent intent1 = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("SquadApp Message @"+groupname)
                        .setContentText(name+": "+message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

                notificationManager.notify(333, mBuilder.build());

                Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    v.vibrate(VibrationEffect.createOneShot(100,VibrationEffect.DEFAULT_AMPLITUDE));
                    v.vibrate(VibrationEffect.createOneShot(100,VibrationEffect.DEFAULT_AMPLITUDE));


                }else{
                    //deprecated in API 26
                    v.vibrate(500);
                    v.vibrate(100);
                    v.vibrate(100);

                }


                abortBroadcast();
            }
            else return;


            // ---send a broadcast intent to update the SMS received in the
            // activity---
            //Intent broadcastIntent = new Intent();
            //broadcastIntent.setAction("SMS_RECEIVED_ACTION");
            //broadcastIntent.putExtra("sms", str);
            //context.sendBroadcast(broadcastIntent);
        }

    }




    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        byte[][] pduObjs = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        byte[][] pdus = new byte[pduObjs.length][];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i = 0; i < pduCount; i++) {
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }

    /**
     * The notification is the icon and associated expanded entry in the status
     * bar.
     */
    protected void showNotification(String message) {
        //Display notification...
    }
}