package com.example.yummy.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.AppManager.ApplicationServiceManager;
import com.example.yummy.AppManager.RecipeApplication;
import com.example.yummy.Model.Note;
import com.example.yummy.Repository.INoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private INoteRepository repository;
    public LiveData<List<Note>> notes = new MutableLiveData<>();

    public NoteViewModel(@NonNull Application application) {
        super(application);
        RecipeApplication recipeApplication = (RecipeApplication) application;
        ApplicationServiceManager manager = recipeApplication;
        repository = manager.getNoteRepository();
    }

    public void getNotes() {
      notes= repository.getNotes();
    }

    public void saveNote(Note note) {
        repository.saveNote(note);

    }
}
