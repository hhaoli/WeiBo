package com.lihonghao.weibo.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        mDb = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    //Remove all the data from all the tables in the database.
    public Observable<Void> clearTable() {
        return Observable.create(subscriber -> {
            BriteDatabase.Transaction transaction = mDb.newTransaction();
            try {
                Cursor cursor = mDb.query("SELECT name FROM sqlite_master WHERE type='table'");
                while (cursor.moveToNext()) {
                    mDb.delete(cursor.getString(cursor.getColumnIndex("name")), null);
                }
                cursor.close();
                transaction.markSuccessful();
                subscriber.onCompleted();
            } finally {
                transaction.end();
            }
        });
    }


    public Observable<Void> setUserBean(UserBean bean) {
        return Observable.create(subscriber -> {
            BriteDatabase.Transaction transaction = mDb.newTransaction();
            try {

//                mDb.delete(Db.UserTable.TABLE_NAME,null)//删除所有数据
                mDb.delete(Db.UserTable.TABLE_NAME, Db.UserTable.COLUMN_NAME, bean.name);//删除一条数据

                ContentValues values = Db.UserTable.toContentValues(bean);
                mDb.insert(Db.UserTable.TABLE_NAME, values);

                transaction.markSuccessful();
                subscriber.onCompleted();
            } finally {
                transaction.end();
            }
        });
    }

    /**
     * 根据name字段查找
     */
    public Observable<UserBean> getUserBean(String name) {
        return Observable.create(subscriber -> {
            Cursor cursor = mDb.query("SELECT * FROM " + Db.UserTable.TABLE_NAME + " WHERE " + Db.UserTable.COLUMN_NAME + "= ?", name);
            while (cursor.moveToNext()) {
                subscriber.onNext(Db.UserTable.parseCursor(cursor));
            }
            cursor.close();
            subscriber.onCompleted();
        });
    }

    public Observable<UserBean> getUserBean(String time, String json) {
        return Observable.create(subscriber -> {
            Cursor cursor = mDb.query(
                    "SELECT * FROM " + Db.UserTable.TABLE_NAME + " WHERE "
                            + Db.UserTable.COLUMN_TIME + " = ? AND "
                            + Db.UserTable.COLUMN_JSON + "= ?",
                    time, json);
            while (cursor.moveToNext()) {
                subscriber.onNext(Db.UserTable.parseCursor(cursor));
            }
            cursor.close();
            subscriber.onCompleted();
        });
    }

    public Observable<String> getUserBeanTime() {
        return Observable.create(subscriber -> {
            Cursor cursor = mDb.query("SELECT DISTINCT " + Db.UserTable.COLUMN_TIME + " FROM " + Db.UserTable.TABLE_NAME);
            while (cursor.moveToNext()) {
                subscriber.onNext(cursor.getString(cursor.getColumnIndexOrThrow(Db.UserTable.COLUMN_TIME)));
            }
            cursor.close();
            subscriber.onCompleted();
        });
    }

}
