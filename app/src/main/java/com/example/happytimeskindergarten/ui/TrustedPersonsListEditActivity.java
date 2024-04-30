package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.happytimeskindergarten.R;

import java.util.ArrayList;

public class TrustedPersonsListEditActivity extends AppCompatActivity implements TrustedPersonAdapter.OnItemListener
{
    RecyclerView recyclerView;
    View selectedPersonView;
    int selectedPersonIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_persons_edit);

        UpdateRecyclerView((ArrayList<Person>) getIntent().getSerializableExtra("trusted_persons_list"));

        View addTrustedPersonButton = findViewById(R.id.addTrustedPersonButton);
        addTrustedPersonButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // добавить доверенное лицо
            }
        });

        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //onBackPressed();
                recyclerView = findViewById(R.id.trustedPersonsRecyclerView);
                TrustedPersonAdapter adapter = (TrustedPersonAdapter)recyclerView.getAdapter();

                Intent intent = new Intent();
                intent.putExtra("persons_arraylist", adapter.personsArraylist);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void UpdateRecyclerView(ArrayList<Person> persons)
    {
        recyclerView = findViewById(R.id.trustedPersonsRecyclerView);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new TrustedPersonAdapter(persons,this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null || selectedPersonView == null) return;

        String full_name = data.getStringExtra("full_name");
        String email = data.getStringExtra("email");
        String phone_number = data.getStringExtra("phone_number");

        TextView fullNameTextView = selectedPersonView.findViewById(R.id.fullNameTextView);
        TextView emailTextView = selectedPersonView.findViewById(R.id.emailTextView);
        TextView phoneNumberTextView = selectedPersonView.findViewById(R.id.phoneNumberTextView);

        fullNameTextView.setText(full_name);
        emailTextView.setText(email);
        phoneNumberTextView.setText(phone_number);

        ArrayList<Person> persons = ((TrustedPersonAdapter)recyclerView.getAdapter()).personsArraylist;
        persons.get(selectedPersonIndex).fullName = full_name;
        persons.get(selectedPersonIndex).email = email;
        persons.get(selectedPersonIndex).phoneNumber = phone_number;
    }

    public void onBackPressed() {
        findViewById(R.id.exitButton).performClick();
        super.onBackPressed();
    }

    @Override
    public void onItemClick(int position, String fullName, String email, String phoneNumber) {
        selectedPersonView = ((TrustedPersonAdapter)recyclerView.getAdapter()).itsLayouts.get(position);
        selectedPersonIndex = position;

        //Toast.makeText(getApplicationContext(), fullName + "_" + email + "_" + phoneNumber, Toast.LENGTH_SHORT).show();

        Intent editIntent = new Intent(this, OnePersonEditActivity.class);
        editIntent.putExtra("full_name", fullName);
        editIntent.putExtra("email", email);
        editIntent.putExtra("phone_number", phoneNumber);

        // передаём данные в интент через putExtra
        startActivityForResult(editIntent, 1);
    }
}