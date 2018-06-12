package com.sproject.ikidz;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:11 PM
 * Email: vihahb@gmail.com
 */
public class iKidApplications extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initRealm();
    }

    private void initRealm() {
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("iKidz_v3.realm")
                .schemaVersion(1)
                .migration(new Migration())
                .build();

        Realm.getInstance(config);
    }

    public static void log(String title, String content) {
        Log.e(title, content);
    }

    public class Migration implements RealmMigration {

        @Override
        public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();

        }
    }
}
