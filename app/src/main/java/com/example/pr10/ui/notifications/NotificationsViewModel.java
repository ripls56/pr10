package com.example.pr10.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pr10.ui.home.HomeViewModel;

public class NotificationsViewModel extends ViewModel {

    private static NotificationsViewModel instance;
    private NotificationsViewModel(){}
    public static NotificationsViewModel getInstance(){
        if(instance == null){
            instance = new NotificationsViewModel();
        }
        return instance;
    }
}