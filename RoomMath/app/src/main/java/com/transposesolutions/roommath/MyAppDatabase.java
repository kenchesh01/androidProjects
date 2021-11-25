package com.transposesolutions.roommath;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {UserDataTable.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase{
    public abstract  MyDao myDao();
    private static MyAppDatabase INSTANCE;

    public static MyAppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyAppDatabase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}