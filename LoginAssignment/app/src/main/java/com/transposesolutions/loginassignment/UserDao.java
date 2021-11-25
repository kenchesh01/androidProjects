package com.transposesolutions.loginassignment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public void  addUser(UserEntity userEntity);

    @Query("SELECT * from users where user_name=(:username) and user_password=(:password)")
    UserEntity login(String username,String password);

    @Query("DELETE FROM users")
    public  void deleteTable();

    @Query("select * from users")
    public List<UserEntity> getUsersList();

}
