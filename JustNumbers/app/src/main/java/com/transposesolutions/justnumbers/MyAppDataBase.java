package com.transposesolutions.justnumbers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserDataTable.class}, version = 1)
public abstract class MyAppDataBase extends RoomDatabase {
    public abstract  MyDao myDao();
    private static MyAppDataBase INSTANCE;

    public static MyAppDataBase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyAppDataBase.class, "DB_NAME")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }

}
