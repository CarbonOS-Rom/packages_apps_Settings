package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.annotation.VisibleForTesting;

import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;

public class CarbonVersionPreferenceController extends BasePreferenceController {

    @VisibleForTesting
    static final String CARBON_VERSION_PROPERTY = "ro.carbon.version.base";
    static final String CARBON_CODENAME_PROPERTY = "ro.carbon.codename";
    static final String CARBON_PACKAGES = "ro.carbon.packagetype";

    public CarbonVersionPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return !TextUtils.isEmpty(SystemProperties.get(CARBON_VERSION_PROPERTY)) && !TextUtils.isEmpty(SystemProperties.get(CARBON_CODENAME_PROPERTY)) && !TextUtils.isEmpty(SystemProperties.get(CARBON_PACKAGES))
                ? AVAILABLE : UNSUPPORTED_ON_DEVICE;
    }

    @Override
    public CharSequence getSummary() {
        String carbonVersion = SystemProperties.get(CARBON_VERSION_PROPERTY);
        String carbonPackage = SystemProperties.get(CARBON_PACKAGES);
        String carbonCodename = SystemProperties.get(CARBON_CODENAME_PROPERTY);
        if (!carbonVersion.isEmpty() && !carbonCodename.isEmpty() && !carbonPackage.isEmpty()) {
            return carbonVersion + " | " + carbonCodename + " | " + carbonPackage;
        } else {
            return
                mContext.getString(R.string.device_info_default);
        }
    }
}
