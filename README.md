
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

actual example of usage:

async getToken () {
		try {

				const isEmulator = DeviceInfo.isEmulator();
				if (isEmulator) {
						return null;
				}

				if (Platform.OS === 'android') {

						const options = {
								senderId: Config.SENDER_ID
						};

						return new Promise((resolve, reject) => {
								RNAndroidPushNotifications.init(
										options,
										(res) => {
												resolve(token);
										},
										(err) => {
												LogService.error('errpushnot', err);
												resolve(null);
										});
						});
				}

				if (Platform.OS === 'ios') {

						return new Promise((resolve, reject) => {
								PushNotificationIOS.addEventListener('registrationError', (message, key) => {
										LogService.error(message);
										resolve(null);
								});
								PushNotificationIOS.addEventListener('register', (token) => {
										resolve(token);
								});

								PushNotificationIOS.requestPermissions();
						});
				}

		} catch (e) {
				LogService.error('getToken exception', e);
		}

		return null;
}

```
  
# Disclaimer

This package is used in production and works! Note that this package
is extremely simple and limited. There is no way to configure anything, except the sender ID.