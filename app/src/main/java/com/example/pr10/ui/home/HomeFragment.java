package com.example.pr10.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pr10.Classes.AppDatabase;
import com.example.pr10.Classes.Balance;
import com.example.pr10.MainActivityViewModel;
import com.example.pr10.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        AppDatabase db = AppDatabase.getDbInstance(getContext());

        var currentUser = MainActivityViewModel.getInstance().getCurrentUser();
        binding.userText.setText("Логин: " + currentUser.getLogin() + ", Пароль: " + currentUser.getPassword());
        var balance = db.balanceDao().getMoneyInfo(currentUser.getId());
        setBalance(balance);

        binding.addmoneyButton.setOnClickListener(view -> {
            try {
                var newBalance = new Balance(Float.parseFloat(binding.moneyEdt.getText().toString()), "Зачисление", binding.descriptionEdt.getText().toString(), currentUser.getId());
                db.balanceDao().insert(newBalance);
                setBalance(newBalance);
            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.withdrawButton.setOnClickListener(view -> {
            try {
                var currentBalance = db.balanceDao().getMoneyInfo(currentUser.getId());
                balance.setCount(currentBalance.getCount() - Float.parseFloat(binding.moneyEdt.getText().toString()));
                db.balanceDao().update(balance);
                setBalance(balance);
            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void setBalance(Balance balance) {
        if(balance == null){
            binding.balanceText.setText(0 + " рублей");
        }
        else{
            binding.balanceText.setText(balance.getCount() + " рублей");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}