package com.example.apptrain.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptrain.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuViewModel extends ViewModel {

    private MutableLiveData<String> mText;



    public MenuViewModel() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Products");

        mText = new MutableLiveData<>();
       // mText.setValue("This is home fragment");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Название").getValue().toString();
                String desc = dataSnapshot.child("Описание").getValue().toString();
                String pathToImg = dataSnapshot.child("Путь к изображению").getValue().toString();
                String count = dataSnapshot.child("Количество").getValue().toString();
                String price = dataSnapshot.child("Цена").getValue().toString();
                Product product = new Product(name,desc,pathToImg,count,price);
                mText.setValue(product.getName());
                //nameText.setValue(desc);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public LiveData<String> getText() {
        return mText;
    }
}