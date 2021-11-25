package com.transposesolutions.justnumbers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    // insert a user input data and status
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  addUsers(UserDataTable userDataTable);
    // get the all users input and result status
    @Query("select * from userdata")
    List<UserDataTable> getUsers1();

    // delete the data table
    @Delete()
    void deleteTable1(UserDataTable userDataTable);

    // get the mistake problems from the data table
    @Query("SELECT * FROM userdata WHERE Status ='\u2718'")
    UserDataTable[] retry();

    // delete data table
    @Query("DELETE FROM userdata")
    void deleteAllData();





}