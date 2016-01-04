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

package io.wazza.android.sdk.service.domain;

import android.content.Context;

import io.wazza.android.sdk.domain.Application;

public class ApplicationService {

    private Context context;

    private Application application;

    public Application getApplication() {
        return application;
    }

    public void setApplicationName() {
        /*
        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo( this.getPackageName(), 0);
        } catch (final NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        */
        int stringId = context.getApplicationInfo().labelRes;
        application.setApplicationName(context.getString(stringId));
    }

    public String getApplicationName() {
        return application.getApplicationName();
    }

    public ApplicationService(Context context) {
        this.context = context;
        application = new Application();
        setApplicationName();
    }

    public ApplicationService(Context context, Application application) {
        this.context = context;
        this.application = application;
    }
}
