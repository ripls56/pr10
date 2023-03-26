package com.example.pr10.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.pr10.Classes.User;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);
    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);

    @Query("select * from user where id_user = :id")
    User getUser(int id);
    @Query("select * from user where login_user = :user_login")
    User getUser(String user_login);

    @Query("select * from user where login_user = :user_login and password_user = :user_password")
    User getAUF(String user_login, String user_password);

    @Query("select * from user")
    List<User> getUsers();
}
