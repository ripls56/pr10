package com.example.pr10.Classes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pr10.Interfaces.BalanceDao;
import com.example.pr10.Interfaces.UserDao;


@Database(entities = {User.class, Balance.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract BalanceDao balanceDao();
    private static  AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                            "mpt.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}

