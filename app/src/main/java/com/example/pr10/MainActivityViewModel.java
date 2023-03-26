package com.example.pr10;

import androidx.lifecycle.ViewModel;

import com.example.pr10.Classes.User;

public class MainActivityViewModel extends ViewModel {

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;

    private static MainActivityViewModel instance;
    private MainActivityViewModel(){}
    public static MainActivityViewModel getInstance(){
        if(instance == null){
            instance = new MainActivityViewModel();
        }
        return instance;
    }
}
