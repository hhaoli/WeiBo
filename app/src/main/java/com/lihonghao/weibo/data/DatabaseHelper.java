package com.lihonghao.weibo.data;

import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
//        mDb = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper);
        mDb = null;
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }
}
