package com.example.administrator.zonglianxi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show1(View view){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentTitle("我是setContentTitle");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.aaa));
        builder.setContentText("我是Content");
        builder.setTicker("我是ticket");

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        PendingIntent pi=PendingIntent.getActivity(this,998,intent,PendingIntent.FLAG_ONE_SHOT);

        builder.setContentIntent(pi);


        Notification bubu = builder.build();
        bubu.flags=Notification.FLAG_INSISTENT|Notification.FLAG_AUTO_CANCEL;

        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,bubu);
    }


    public void show2(View view){
        NotificationCompat.InboxStyle style=new NotificationCompat.InboxStyle();

            style.addLine("你好1");
        style.addLine("你好2");
        style.addLine("你好3");
        style.addLine("你好4");
        style.addLine("你好5");


        NotificationCompat.Builder builder2=new NotificationCompat.Builder(this);
        builder2.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("聊天记录")
                .setStyle(style);

        NotificationManager manager2= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager2.notify(1,builder2.build());
    }

    public void show3(View view){
        final NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("正在下载");
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        new Thread(){
            @Override
            public void run(){
                for(int i = 0; i < 101; i++){

                    if(i==100){
                        builder.setContentTitle("下载完成");
                    }
                    builder.setProgress(100,i,false);
                    builder.setContentText(i+"/"+100);

                    manager.notify(22,builder.build());
                    SystemClock.sleep(50);


                }

            }
        }.start();



    }
}
