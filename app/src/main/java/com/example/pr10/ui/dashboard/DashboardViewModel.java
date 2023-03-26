package com.example.pr10.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private static DashboardViewModel instance;
    private DashboardViewModel(){}
    public static DashboardViewModel getInstance(){
        if(instance == null){
            instance = new DashboardViewModel();
        }
        return instance;
    }

}