package com.lihonghao.weibo.data.local;

import android.content.ContentValues;
import android.database.Cursor;

public class Db {
    public Db() {
    }

    public static final class UserTable {
        public static final String TABLE_NAME = "user";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_JSON = "json";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " TEXT PRIMARY KEY," +
                        COLUMN_NAME + " TEXT NOT NULL," +
                        COLUMN_TIME + " TEXT NOT NULL," +
                        COLUMN_JSON + " TEXT NOT NULL" +
                        " );";

        public static ContentValues toContentValues(UserBean bean) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, bean.id);
            values.put(COLUMN_NAME, bean.name);
            values.put(COLUMN_TIME, bean.time);
            values.put(COLUMN_JSON, bean.json);
            return values;
        }

        public static UserBean parseCursor(Cursor cursor) {
            UserBean bean = new UserBean();
            bean.id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
            bean.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            bean.time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME));
            bean.json = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JSON));
            return bean;
        }
    }
}
