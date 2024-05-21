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
    ArrayList<Person> trustedPersons;
    RecyclerView recyclerView;
    TrustedPersonAdapter adapter;
    View selectedPersonView;
    int selectedPersonIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_persons_edit);

        recyclerView = findViewById(R.id.trustedPersonsRecyclerView);
        adapter = (TrustedPersonAdapter)recyclerView.getAdapter();
        UpdateRecyclerView((ArrayList<Person>) getIntent().getSerializableExtra("trusted_persons_list"));

        View addTrustedPersonButton = findViewById(R.id.addTrustedPersonButton);
        addTrustedPersonButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TrustedPersonsListEditActivity.this,
                        PersonEditWithoutDeletingActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                adapter = (TrustedPersonAdapter)recyclerView.getAdapter();
                Intent intent = new Intent();
                intent.putExtra("persons_arraylist", adapter.personsArraylist);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void UpdateRecyclerView(ArrayList<Person> persons)
    {
        trustedPersons = persons;
        recyclerView = findViewById(R.id.trustedPersonsRecyclerView);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new TrustedPersonAdapter(persons,this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;

        if (requestCode == 1) {
            if (selectedPersonView == null) return;

            Person trustedPerson = (Person)data.getSerializableExtra(Person.class.getSimpleName());

            Boolean is_deleted = data.getBooleanExtra("is_deleted", false);

            if (is_deleted) {
                TrustedPersonAdapter adapter = (TrustedPersonAdapter) recyclerView.getAdapter();
                adapter.personsArraylist.remove(selectedPersonIndex);
                UpdateRecyclerView(adapter.personsArraylist);
                return;
            }

            TextView fullNameTextView = selectedPersonView.findViewById(R.id.fullNameTextView);
            TextView emailTextView = selectedPersonView.findViewById(R.id.emailTextView);
            TextView phoneNumberTextView = selectedPersonView.findViewById(R.id.phoneNumberTextView);

            fullNameTextView.setText(trustedPerson.getFullName());
            emailTextView.setText(trustedPerson.getEmail());
            phoneNumberTextView.setText(trustedPerson.getPhoneNumber());

            ArrayList<Person> persons = ((TrustedPersonAdapter) recyclerView.getAdapter()).personsArraylist;
            persons.get(selectedPersonIndex).setFullName(trustedPerson.getFullName());
            persons.get(selectedPersonIndex).setEmail(trustedPerson.getEmail());
            persons.get(selectedPersonIndex).setPhoneNumber(trustedPerson.getPhoneNumber());
        } else if (requestCode == 2) {
            Person person = (Person)data.getSerializableExtra("parent");
            adapter = (TrustedPersonAdapter) recyclerView.getAdapter();
            adapter.personsArraylist.add(person);
            UpdateRecyclerView(adapter.personsArraylist);
        }
    }

    public void onBackPressed() {
        findViewById(R.id.exitButton).performClick();
        super.onBackPressed();
    }

    @Override
    public void onItemClick(int position, Person person) {
        selectedPersonView = ((TrustedPersonAdapter)recyclerView.getAdapter()).itsLayouts.get(position);
        selectedPersonIndex = position;

        //Toast.makeText(getApplicationContext(), fullName + "_" + email + "_" + phoneNumber, Toast.LENGTH_SHORT).show();

        Intent editIntent = new Intent(this, OnePersonEditActivity.class);
        editIntent.putExtra(Person.class.getSimpleName(), person);

        // передаём данные в интент через putExtra
        startActivityForResult(editIntent, 1);
    }
}