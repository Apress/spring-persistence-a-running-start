package com.smartpants.artwork.util;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 3, 2006
 * Time: 5:49:27 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class ViewUtil {

    public static List<String> buildAlternator(String[] itemsToIterate, int loops) {
        List<String> alternateList = new ArrayList<String>();
        int innerIndex = 0;
        for (int i = 0; i < loops; i++) {
            if (innerIndex >= itemsToIterate.length)
                innerIndex = 0;
            alternateList.add(itemsToIterate[innerIndex++]);
        }
        return alternateList;
    }

}
