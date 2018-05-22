package com.example.android.sha;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by dam2a on 10/05/18.
 */

public class SHAUtils {
    public static String getAndroidId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
