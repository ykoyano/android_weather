package com.example.user.weather.request;

import io.realm.DynamicRealm;

import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        if (oldVersion == 0) {
            RealmSchema schema = realm.getSchema();
            schema.create("Person")
                    .addField("name", String.class)
                    .addField("age", int.class);
            oldVersion++;
        }
    }
}
