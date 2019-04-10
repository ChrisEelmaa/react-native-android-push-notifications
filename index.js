
import { NativeModules } from 'react-native';

const { RNAndroidPushNotifications } = NativeModules;

export default {
    unregister: (success, failure) => {
        RNAndroidPushNotifications.unregister(success, failure);
    },

    init: (options, success, failure) => {
        RNAndroidPushNotifications.init(options, success, failure);
    }
};
