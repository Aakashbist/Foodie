package com.aakashbista.foodie.Repository;

import androidx.lifecycle.LiveData;

import com.aakashbista.foodie.Model.Note;

import java.util.List;

public interface INoteRepository {
    List<Note> getNotes();

    LiveData< List<Note>>  getNotesLiveDate();

    void saveNote(Note note);

    void deleteNote(int position);


}
