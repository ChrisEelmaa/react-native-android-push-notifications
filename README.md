
# react-native-android-push-notifications

## Getting started

`$ npm install react-native-android-push-notifications --save`

### Automatic installation

`$ react-native link react-native-android-push-notifications`

### Further set up

In order to use android push notifications (FCM) it is necessary
to configure Firebase dependencies.

1) Create firebase project in google + get google-services.json file, and place it in the "android/app" folder.

2) My ext settings in android/build.gradle
```
ext {
		buildToolsVersion = "28.0.2"
		minSdkVersion = 16
		compileSdkVersion = 28
		targetSdkVersion = 27
		supportLibVersion = "26.1.0"
		googlePlayServicesVersion = "15.0.0"
		firebaseVersion = "15.0.2"
}
```
3) My dependencies in android/build.gradle
```
dependencies {
		classpath 'com.android.tools.build:gradle:3.2.1'
		classpath 'com.google.gms:google-services:4.0.2'
}
```

4) Import following projs in android/app/build.gradle (under dependencies):
```
implementation "com.google.android.gms:play-services-base:${rootProject.ext.googlePlayServicesVersion}"

implementation "com.google.firebase:firebase-core:${rootProject.ext.firebaseVersion}"
```

5) Add apply to the end of the file in android/app/build.gradle
```
apply plugin: 'com.google.gms.google-services'
```

## Usage
```javascript
import RNAndroidPushNotifications from 'react-native-android-push-notifications';

RNAndroidPushNotifications.init(
		options,
		(token) => {
				alert("token" + token)
		},
		(err) => {
				alert(err)
		});


// In order to "invalidate" a token
// This can happen when user logs out from your app.
RNAndroidPushNotifications.unregister(
		() => {},
		() => {}
);

```
  
# Disclaimer

This package is used in production and works! Note that this package
is extremely simple and limited. There is no way to configure anything.