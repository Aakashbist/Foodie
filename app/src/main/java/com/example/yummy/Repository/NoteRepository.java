package com.example.yummy.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.Data.Preference;
import com.example.yummy.Model.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository implements INoteRepository {
    private Context context;
    private Preference preference;
    private MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    private String key = "my_note";
    private List<Note> list = new ArrayList<>();

    private Gson gson = new Gson();
    static NoteRepository obj;

    public static NoteRepository getInstance(Context context) {

        if (obj != null) {
            return obj;
        }
        obj = new NoteRepository(context);
        return obj;
    }


    private NoteRepository(Context context) {
        this.context = context;
        preference = new Preference(context);

    }

    @Override
    public LiveData<List<Note>> getNotes() {

        String json = preference.getString(key);
        Type type = new TypeToken<ArrayList<Note>>() {
        }.getType();
        list = gson.fromJson(json, type);
        notes.setValue(list);
        return notes;
    }

    @Override
    public void saveNote(Note note) {
        list = notes.getValue();
        list.add(note);
        notes.setValue(list);
        String updatedNotes = gson.toJson(list);
        preference.putString(key, updatedNotes);
        getNotes();
    }

    @Override
    public void deleteNote() {

    }
}
