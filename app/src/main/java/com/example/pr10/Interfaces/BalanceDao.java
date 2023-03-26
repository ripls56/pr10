package com.example.pr10.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pr10.Classes.Balance;

import java.util.List;

@Dao
public interface BalanceDao {
    @Insert
    void insert(Balance balance);
    @Update
    void update(Balance balance);
    @Delete
    void delete(Balance balance);

    @Query("select id_balance, sum(count) as count, type, description, user_id from balance where user_id = :userId")
    Balance getMoneyInfo(int userId);

    @Query("select id_balance, sum(count) as count, type, description, user_id from balance where type = 'Оплата' and user_id = :userId")
    Balance getSumPayBalanceInfo(int userId);
    @Query("select id_balance, sum(count) as count, type, description, user_id from balance where type = 'Оплата' and user_id = :userId GROUP by description")
    List<Balance> getPayBalanceInfo(int userId);

    @Query("select id_balance, sum(count) as count, type, description, user_id from balance where type = 'Зачисление' and user_id = :userId")
    Balance getSumSalaryBalanceInfo(int userId);

    @Query("select id_balance, sum(count) as count, type, description, user_id from balance where type = 'Зачисление' and user_id = :userId GROUP by description")
    List<Balance> getSalaryBalanceInfo(int userId);


}
