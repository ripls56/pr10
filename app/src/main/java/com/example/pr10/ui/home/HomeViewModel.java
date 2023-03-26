package com.example.pr10.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pr10.Classes.User;

public class HomeViewModel extends ViewModel {



    private static HomeViewModel instance;
    private HomeViewModel(){}
    public static HomeViewModel getInstance(){
        if(instance == null){
            instance = new HomeViewModel();
        }
        return instance;
    }
}