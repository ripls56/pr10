package com.example.pr10.Classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Balance", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id_user",
        childColumns = "user_id"))
public class Balance {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_balance")
    private int idBalance;

    @ColumnInfo(name = "count")
    private float count;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "user_id")
    private int userId;


    public Balance(float count, String type, String description, int userId) {
        this.count = count;
        this.type = type;
        this.description = description;
        this.userId = userId;
    }

    @Ignore
    public Balance(int idBalance, float count, String type, String description, int userId) {
        this.idBalance = idBalance;
        this.count = count;
        this.type = type;
        this.description = description;
        this.userId = userId;
    }

    public int getIdBalance() {
        return idBalance;
    }

    public void setIdBalance(int idBalance) {
        this.idBalance = idBalance;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
