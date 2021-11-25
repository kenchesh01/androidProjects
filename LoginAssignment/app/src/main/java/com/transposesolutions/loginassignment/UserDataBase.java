package com.transposesolutions.loginassignment;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract  class UserDataBase extends RoomDatabase {
    private static final String dbName = "user";
    private static UserDataBase userDataBase;

    public static synchronized UserDataBase getUserDataBase(Context context){
        if(userDataBase ==null){
            userDataBase = Room.databaseBuilder(context,UserDataBase.class,dbName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return userDataBase;
    }
    public  abstract  UserDao userDao();
}
