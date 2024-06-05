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

import com.example.happytimeskindergarten.R;
import com.example.happytimeskindergarten.ui.*;
import retrofit2.*;
import com.example.happytimeskindergarten.ui.Person;
import com.example.happytimeskindergarten.ui.PersonEditWithoutDeletingActivity;
import com.example.happytimeskindergarten.ui.TrustedPersonAdapter;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ParentsFragment extends Fragment implements TrustedPersonAdapter.OnItemListener
{
    private ParentsViewModel mViewModel;
    RecyclerView recyclerView;
    TrustedPersonAdapter adapter;
    ArrayList<Person> trustedPersonsList;
    Person parent;
    View selectedPersonView;
    int selectedPersonIndex;



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

        recyclerView = getActivity().findViewById(R.id.trustedPersonsRecyclerView);
        adapter = (TrustedPersonAdapter)recyclerView.getAdapter();

        ////////////////////////////////////////////////////////////////////////////
        // Заполнение динамического списка доверенных лиц
        // Содержимое списка чисто для проверки работоспособности, а так заполняем с сервера

        parent = new Person();
        trustedPersonsList = new ArrayList<Person>();

        View addTrustedPersonButton = getActivity().findViewById(R.id.addTrustedPersonButton);
        addTrustedPersonButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonEditWithoutDeletingActivity.class);
                intent.putExtra(Person.class.getSimpleName(), parent);
                startActivityForResult(intent, 2);
            }
        });

        // Заполняем recyclerView доверенными лицами
        UpdateRecyclerView();



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
        /*View trustedPersonsEditButton = getView().findViewById(R.id.trustedPersonsEditButton);
        trustedPersonsEditButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), TrustedPersonsListEditActivity.class);
                intent.putExtra("trusted_persons_list", trustedPersonsList);
                startActivityForResult(intent, 2);
            }
        });*/
    }
    public void UpdateRecyclerView() {
        // Получаем RecyclerView и проверяем его на null
        RecyclerView trustedPersonsRecyclerView = getActivity().findViewById(R.id.trustedPersonsRecyclerView);
        if (trustedPersonsRecyclerView == null) {
            Log.e("ParentsFragment", "trustedPersonsRecyclerView is null");
            return;
        }

        // Создаем адаптер и LayoutManager для RecyclerView
        TrustedPersonAdapter adapter = new TrustedPersonAdapter(trustedPersonsList, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 1);

        // Устанавливаем LayoutManager и адаптер для RecyclerView
        trustedPersonsRecyclerView.setLayoutManager(layoutManager);
        trustedPersonsRecyclerView.setAdapter(adapter);

        // Получаем family_account_id и токен пользователя
        String familyAccountId = User.getFamily_account_id() != null && User.getFamily_account_id().length > 0 ? User.getFamily_account_id()[0] : null;
        String token = User.getToken();

        // Проверяем familyAccountId и token на null
        if (familyAccountId == null || token == null) {
            Log.e("ParentsFragment", "Family account ID or token is null");
            return;
        }

        // Выполняем запрос для получения данных о семье
        Request.requestfamily.getfamily(familyAccountId, token).enqueue(new Callback<family_accountData>() {
            @Override
            public void onResponse(Call<family_accountData> call, Response<family_accountData> response) {
                if (response.body() == null || response.body().getData() == null) {
                    Log.e("ParentsFragment", "Family account data is null");
                    return;
                }

                User.setFamily_account(response.body());
                family_accountData.Data familyData = response.body().getData();

                Log.d("ParentsFragment", "Received family data: " + familyData.getName() + ", " + familyData.getEmail() + ", " + familyData.getPhone());

                parent = new Person();
                parent.setId(familyData.getUser_id());
                parent.setFullName(familyData.getName());
                parent.setEmail(familyData.getEmail());
                parent.setPhoneNumber(familyData.getPhone());

                View parentBlock = getActivity().findViewById(R.id.parentBlock);
                if (parentBlock == null) {
                    Log.e("ParentsFragment", "parentBlock is null");
                    return;
                }

                ((TextView)parentBlock.findViewById(R.id.fullNameTextView)).setText(parent.getFullName());
                ((TextView)parentBlock.findViewById(R.id.emailTextView)).setText(parent.getEmail());
                ((TextView)parentBlock.findViewById(R.id.phoneNumberTextView)).setText(parent.getPhoneNumber());
                if (familyData.getImage_data() != null) {
                    ((ShapeableImageView)parentBlock.findViewById(R.id.profileImage_0)).setImageBitmap(Base64image.decode_image(familyData.getImage_data()));
                }

                trustedPersonsList = new ArrayList<>();
                for (int trustedPersonId : familyData.getTrusted_persons()) {
                    Request.requestTrustedPerson.getTrustedPerson(String.valueOf(trustedPersonId), token).enqueue(new Callback<TrustedPersonData>() {
                        @Override
                        public void onResponse(Call<TrustedPersonData> call, Response<TrustedPersonData> response) {
                            if (response.body() == null || response.body().getData() == null) {
                                Log.e("ParentsFragment", "Trusted person data is null");
                                return;
                            }

                            Person person = new Person();
                            person.setId(response.body().getData().getId());
                            person.setFullName(response.body().getData().getName());
                            person.setEmail(response.body().getData().getEmail());
                            person.setPhoneNumber(response.body().getData().getPhone());
                            person.setImageData(response.body().getData().getImage_data());
                            trustedPersonsList.add(person);
                            adapter.loadTrustedPersons(trustedPersonsList);
                        }

                        @Override
                        public void onFailure(Call<TrustedPersonData> call, Throwable t) {
                            Log.e("ParentsFragment", "Failed to fetch trusted person data", t);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<family_accountData> call, Throwable t) {
                Log.e("ParentsFragment", "Failed to fetch family account data", t);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        parent = new Person();
        trustedPersonsList = new ArrayList<Person>();
        UpdateRecyclerView();

        /*if(requestCode == )
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
        }*/
    }

    @Override
    public void onItemClick(int position, Person person) {
        // здесь можно указать, что будет, если пользователь нажмёт на элемент из recyclerView
        selectedPersonView = ((TrustedPersonAdapter)recyclerView.getAdapter()).itsLayouts.get(position);
        selectedPersonIndex = position;

        //Toast.makeText(getApplicationContext(), fullName + "_" + email + "_" + phoneNumber, Toast.LENGTH_SHORT).show();

        Intent editIntent = new Intent(getActivity(), OnePersonEditActivity.class);
        editIntent.putExtra(Person.class.getSimpleName(), person);

        // передаём данные в интент через putExtra
        startActivityForResult(editIntent, 1);
    }
}