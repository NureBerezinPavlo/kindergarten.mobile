package com.example.happytimeskindergarten.ui.parents;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happytimeskindergarten.R;
import com.example.happytimeskindergarten.ui.OnePersonEditActivity;
import com.example.happytimeskindergarten.ui.Person;
import com.example.happytimeskindergarten.ui.PersonEditWithoutDeletingActivity;
import com.example.happytimeskindergarten.ui.TrustedPersonAdapter;
import com.example.happytimeskindergarten.ui.TrustedPersonsListEditActivity;

import java.util.ArrayList;

public class ParentsFragment extends Fragment implements TrustedPersonAdapter.OnItemListener
{
    private ParentsViewModel mViewModel;
    ArrayList<Person> personsList = new ArrayList<Person>() {{
        add(new Person("Березін Павло Павлович", "pberezin97@gmail.com", "+880-333-26-05"));
        add(new Person("Лантінов Володимир", "lantiniff@gmail.com", "+8-800-555-35-35"));
        add(new Person("Сахань Дмитро", "dimka@mail.com", "+333-696-96-96"));
    }};

    public static ParentsFragment newInstance() {
        return new ParentsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_parents, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ParentsViewModel.class);

        // Заполнение динамического списка доверенных лиц
        // Содержимое списка чисто для проверки работоспособности, а так заполняем с сервера
        Person parent = new Person("Ковальов Богдан Сергійович", "bogdan@gmail.com", "+380-666-14-88");

        // Заполняем recyclerView доверенными лицами
        UpdateRecyclerView(personsList);

        // Заполняем данные о родителе
        View parentBlock = getActivity().findViewById(R.id.parentBlock);
        ((TextView)parentBlock.findViewById(R.id.fullNameTextView)).setText(parent.fullName);
        ((TextView)parentBlock.findViewById(R.id.emailTextView)).setText(parent.email);
        ((TextView)parentBlock.findViewById(R.id.phoneNumberTextView)).setText(parent.phoneNumber);

        // кнопка редактирования родителя, на которого зареган семейный акк
        View parentEditButton = getView().findViewById(R.id.parentEditButton);
        parentEditButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), PersonEditWithoutDeletingActivity.class);
                intent.putExtra(Person.class.getSimpleName(), parent);
                startActivityForResult(intent, 3);
            }
        });

        // Кнопка редактирования доверенных лиц
        View trustedPersonsEditButton = getView().findViewById(R.id.trustedPersonsEditButton);
        trustedPersonsEditButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), TrustedPersonsListEditActivity.class);
                intent.putExtra("trusted_persons_list", personsList);
                startActivityForResult(intent, 2);
            }
        });
    }
    public void UpdateRecyclerView(ArrayList<Person> personsList)
    {
        RecyclerView trustedPersonsRecyclerView =
                getActivity().findViewById(R.id.trustedPersonsRecyclerView);

        TrustedPersonAdapter adapter = new TrustedPersonAdapter(personsList, this);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(requireContext(), 1);

        trustedPersonsRecyclerView.setLayoutManager(layoutManager);
        trustedPersonsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null) return;

        ArrayList<Person> temporaryPersonsList = (ArrayList<Person>) data.getSerializableExtra("persons_arraylist");
        if(temporaryPersonsList != null)
        {
            personsList = temporaryPersonsList;
            UpdateRecyclerView(personsList);
        }

        Person parent = (Person)data.getSerializableExtra("parent");
        if(parent != null)
        {
            View parentBlock = getActivity().findViewById(R.id.parentBlock);
            ((TextView)parentBlock.findViewById(R.id.fullNameTextView)).setText(parent.fullName);
            ((TextView)parentBlock.findViewById(R.id.emailTextView)).setText(parent.email);
            ((TextView)parentBlock.findViewById(R.id.phoneNumberTextView)).setText(parent.phoneNumber);
        }
    }

    @Override
    public void onItemClick(int position, String fullName, String email, String phoneNumber) {
        // здесь можно указать, что будет, если пользователь нажмёт на элемент из recyclerView
    }
}