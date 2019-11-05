package com.example.apptrain.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.apptrain.R;

public class MenuFragment extends Fragment {

    private MenuViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(MenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        final TextView name = root.findViewById(R.id.text_name);
        final TextView desc = root.findViewById(R.id.text_desc);
        final TextView pathToImg = root.findViewById(R.id.text_pathToImg);
        final TextView count = root.findViewById(R.id.text_count);
        final TextView price = root.findViewById(R.id.text_price);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                name.setText(s);
            }
        });
        return root;
    }
}