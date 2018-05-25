package com.itstrongs.library.utils;

import android.support.annotation.Nullable;

/**
 * Created by itstrongs on 2017/10/23.
 */
public class ActivityUtils {

    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if(reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }
}
