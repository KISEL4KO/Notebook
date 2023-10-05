package com.example.myapplication.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Notebook;
import com.example.myapplication.view.UpdateActivity;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    //поля Адаптера
    private Context context; // context
    private  Activity activity;// activity
    private List<Notebook> notesList;// all notes
    private List<Notebook> newList; // new note

    public Adapter(Context contex,Activity activity,List<Notebook> notesList){
        this.context = contex;
        this.activity = activity;
        this.notesList = notesList;
        newList = new ArrayList<>(notesList);
    }

    //метод onCreateViewHolder возвращает обьект ViewHolder, который будет хранить данные по одному обьекту Notebook

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
       // трансформация layout-файла во View-элемент
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    // метод onBindViewHolder выполняет привязку обьекта ViewHolder к обьекту Notebook по определенной позиции
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.title.setText(notesList.get(position).getTitle());
        holder.description.setText(notesList.get(position).getDescription());

        // обработаем нажатие на контейнер notes_recycle_view
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // переключение на новый экран
                Intent intent = new Intent(context, UpdateActivity.class);
                //передача данных в новую активити
                intent.putExtra("title", notesList.get(position).getTitle());
                intent.putExtra("description",notesList.get(position).getDescription());
                intent.putExtra("id",notesList.get(position).getId());
                // старт перехода
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


    // созданный статический класс ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //поля представлении
        TextView title,description;
        ConstraintLayout mLayout;

        //конструктор ViewHolder
        ViewHolder(@NonNull View view){
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            mLayout = view.findViewById(R.id.mLayout);
        }
    }
}
