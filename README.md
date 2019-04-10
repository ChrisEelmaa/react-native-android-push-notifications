
# react-native-android-push-notifications

## Getting started

`$ npm install react-native-android-push-notifications --save`

### Automatic installation

`$ react-native link react-native-android-push-notifications`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNAndroidPushNotificationsPackage;` to the imports at the top of the file
  - Add `new RNAndroidPushNotificationsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-push-notifications'
  	project(':react-native-android-push-notifications').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-android-push-notifications/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-push-notifications')
  	```

## Usage
```javascript
import RNAndroidPushNotifications from 'react-native-android-push-notifications';

RNAndroidPushNotifications.init(
		options,
		(res) => {
				resolve(token);
		},
		(err) => {
				LogService.error('errpushnot', err);
				resolve(null);
		});

```
  
# Disclaimer

This package is used in production and works! Note that this package
is extremely simple and limited. There is no way to configure anything, except the sender ID.