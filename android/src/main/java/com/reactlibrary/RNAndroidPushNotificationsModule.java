
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.os.Build;
import android.annotation.TargetApi;


public class RNAndroidPushNotificationsModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNAndroidPushNotificationsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAndroidPushNotifications";
  }


  @TargetApi(26)
  private void createDefaultNotificationChannel() {

    // only call on Android O and above
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

      try {
        String channelId = "PushPluginChannel";
        String description = "PhoneGap PushPlugin";

        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(channelId, description, importance);

        NotificationManager notificationManager = reactContext.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        
      } catch (Exception e) {}
    }
  }

  @ReactMethod
    public void unregister(
        Callback successCallback,
        Callback errorCallback) {
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
            successCallback.invoke();
        } catch (Exception e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void init(
        ReadableMap options,
        Callback successCallback,
        Callback errorCallback) {

        String token = null;
        String senderID = null;

        try {

            senderID = options.getString("senderId");

            try {
              token = FirebaseInstanceId.getInstance().getToken();
            } catch (IllegalStateException e) {}

            if (token == null) {
              try {
                token = FirebaseInstanceId.getInstance().getToken(senderID, "FCM");
              } catch (IllegalStateException e) {}
            }

            if (!"".equals(token)) {
              createDefaultNotificationChannel();
              successCallback.invoke(token);
            } else {
              errorCallback.invoke("Empty registration ID received from FCM");
              return;
            }
        } catch (Exception e) {
            errorCallback.invoke(e.getMessage());
        }
    }
}