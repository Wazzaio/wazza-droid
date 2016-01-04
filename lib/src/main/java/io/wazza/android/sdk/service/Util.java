/*
 * wazza-droid
 * https://github.com/Wazzaio/wazza-droid
 * Copyright (C) 2013-2015  Duarte Barbosa, João Vazão Vasques
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.wazza.android.sdk.service;

import android.text.format.Time;

public class Util {

    public static String getCurrentTime() {
        Time now = new Time();
        now.setToNow();
        String seconds = now.format("%k:%M:%S");
        return seconds;
    }


    public static String capitalize(String s) {
        if (s == null || s.length() == 0)
            return "";

        char first = s.charAt(0);

        if (Character.isUpperCase(first))
            return s;
        else
            return Character.toUpperCase(first) + s.substring(1);
    }

}
