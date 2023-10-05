package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Notebook;
import com.example.myapplication.viewmodel.Adapter;
import com.example.myapplication.viewmodel.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView; // поле для списка RecyclerView
    private FloatingActionButton fabAdd;// поле для кнопки добавить новую заметку

    private List<Notebook> notesList;// поле для контейнера списка заметок

    private DatabaseHelper database;// поле работы с БД
    private Adapter adapter; //поле для adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // присваение ID  полям
        recyclerView = findViewById(R.id.recycler_list);
        fabAdd = findViewById(R.id.fabAdd);

        notesList = new ArrayList<>();
        database = new DatabaseHelper(this);
        //считывание данных из БД и запись их в коллекцию notesList
        fetchAllNotes();

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // задание структуры вывода данных в recyclerView
        adapter = new Adapter(this,SecondActivity.this,notesList); // инцилизация адаптера и передача в него данных из БД
        recyclerView.setAdapter(adapter); // передача в recyclerView адаптер

        //обработка нажатия кнопки для создания новой заметки
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // переключение на новую активность
                startActivity(new Intent(SecondActivity.this,AddNotesActivity.class));
            }
        });
    }
    // метод считывания из БД всех записей
    public void fetchAllNotes(){
        //ridding BD
        Cursor cursor = database.readNotes();

        if(cursor.getCount() == 0){// если данные отсутсвуют ,то вывод на экран об этом
            Toast.makeText(this, "Заметок нет", Toast.LENGTH_SHORT).show();
        } else {// иначе помещение их в контейнер данных списка
            while(cursor.moveToNext()){
                // помещение в контенер из курсора
                notesList.add(new Notebook(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }
        }
    }
}