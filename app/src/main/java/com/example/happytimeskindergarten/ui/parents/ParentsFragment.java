package com.example.happytimeskindergarten.ui.parents;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happytimeskindergarten.R;
import com.example.happytimeskindergarten.ui.*;
import retrofit2.*;
import com.example.happytimeskindergarten.ui.Person;
import com.example.happytimeskindergarten.ui.PersonEditWithoutDeletingActivity;
import com.example.happytimeskindergarten.ui.TrustedPersonAdapter;
import com.example.happytimeskindergarten.ui.TrustedPersonsListEditActivity;

import java.util.ArrayList;

public class ParentsFragment extends Fragment implements TrustedPersonAdapter.OnItemListener
{
    private ParentsViewModel mViewModel;
    Person parent;
    ArrayList<Person> trustedPersonsList;

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

        ////////////////////////////////////////////////////////////////////////////
        // Заполнение динамического списка доверенных лиц
        // Содержимое списка чисто для проверки работоспособности, а так заполняем с сервера

        parent = new Person();


        trustedPersonsList = new ArrayList<Person>();


        // Заполняем recyclerView доверенными лицами
        UpdateRecyclerView(trustedPersonsList);

        // Заполняем данные о родителе
        View parentBlock = getActivity().findViewById(R.id.parentBlock);
        ((TextView)parentBlock.findViewById(R.id.fullNameTextView)).setText(parent.getFullName());
        ((TextView)parentBlock.findViewById(R.id.emailTextView)).setText(parent.getEmail());
        ((TextView)parentBlock.findViewById(R.id.phoneNumberTextView)).setText(parent.getPhoneNumber());

        // кнопка редактирования родителя, на которого зареган семейный акк
        View parentEditButton = getView().findViewById(R.id.parentEditButton);
        parentEditButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), PersonEditWithoutDeletingActivity.class);
                intent.putExtra(Person.class.getSimpleName(), parent);
                intent.putExtra("create", false);
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
                intent.putExtra("trusted_persons_list", trustedPersonsList);
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
        Request.requestfamily.getfamily(User.getFamily_account_id()[0], User.getToken()).enqueue(new Callback<family_accountData>() {
            @Override
            public void onResponse(Call<family_accountData> call, Response<family_accountData> response) {

                User.setFamily_account(response.body());
                System.out.println(response.body().getData().getName() + response.body().getData().getEmail() + response.body().getData().getPhone());
                parent = new Person();
                parent.setId(response.body().getData().getUser_id());
                parent.setFullName(response.body().getData().getName());
                parent.setEmail(response.body().getData().getEmail());
                parent.setPhoneNumber(response.body().getData().getPhone());
                View parentBlock = getActivity().findViewById(R.id.parentBlock);
                ((TextView)parentBlock.findViewById(R.id.fullNameTextView)).setText(parent.getFullName());
                ((TextView)parentBlock.findViewById(R.id.emailTextView)).setText(parent.getEmail());
                ((TextView)parentBlock.findViewById(R.id.phoneNumberTextView)).setText(parent.getPhoneNumber());
                trustedPersonsList = new ArrayList<Person>();
                for(int i = 0; i < response.body().getData().getTrusted_persons().length; i++){
                    Request.requestTrustedPerson.getTrustedPerson(String.valueOf(response.body().getData().getTrusted_persons()[i]), User.getToken()).enqueue(new Callback<TrustedPersonData>() {
                        @Override
                        public void onResponse(Call<TrustedPersonData> call, Response<TrustedPersonData> response) {
                            Person person = new Person();
                            person.setId(response.body().getData().getId());
                            person.setFullName(response.body().getData().getName());
                            person.setEmail(response.body().getData().getEmail());
                            person.setPhoneNumber(response.body().getData().getPhone());
                            trustedPersonsList.add(person);
                            adapter.loadTrustedPersons(trustedPersonsList);
                        }

                        @Override
                        public void onFailure(Call<TrustedPersonData> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<family_accountData> call, Throwable t) {
                Log.e("Error","Errror",t);
                System.out.println("Error");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null) return;

        ArrayList<Person> temporaryPersonsList = (ArrayList<Person>) data.getSerializableExtra("persons_arraylist");
        if(temporaryPersonsList != null)
        {
            trustedPersonsList = temporaryPersonsList;
            UpdateRecyclerView(trustedPersonsList);
        }

        Person newParent = (Person)data.getSerializableExtra("parent");
        if(newParent != null)
        {
            parent = newParent;
            View parentBlock = getActivity().findViewById(R.id.parentBlock);
            ((TextView)parentBlock.findViewById(R.id.fullNameTextView)).setText(parent.getFullName());
            ((TextView)parentBlock.findViewById(R.id.emailTextView)).setText(parent.getEmail());
            ((TextView)parentBlock.findViewById(R.id.phoneNumberTextView)).setText(parent.getPhoneNumber());
        }
    }

    @Override
    public void onItemClick(int position, Person person) {
        // здесь можно указать, что будет, если пользователь нажмёт на элемент из recyclerView
    }
}