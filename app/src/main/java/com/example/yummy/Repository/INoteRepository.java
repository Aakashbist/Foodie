package com.example.yummy.Repository;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.Note;

import java.util.List;

public interface INoteRepository {
    LiveData<List<Note>> getNotes();

    void saveNote(Note note);

    void deleteNote();


}
