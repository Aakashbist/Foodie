package com.aakashbista.foodie.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aakashbista.foodie.AppManager.ApplicationServiceManager;
import com.aakashbista.foodie.AppManager.RecipeApplication;
import com.aakashbista.foodie.Model.Note;
import com.aakashbista.foodie.Repository.INoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private INoteRepository repository;
    public LiveData<List<Note>> notes = new MutableLiveData<>();

    public NoteViewModel(@NonNull Application application) {
        super(application);
        RecipeApplication recipeApplication = (RecipeApplication) application;
        ApplicationServiceManager manager = recipeApplication;
        repository = manager.getNoteRepository();
        notes = repository.getNotesLiveDate();
    }

    public void saveNote(Note note) {
        repository.saveNote(note);
    }

    public void deleteNote(int position) {
        repository.deleteNote(position);
    }
}
