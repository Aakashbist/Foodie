package com.example.yummy.UI.Fragment;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Adapter.NoteAdapter;
import com.example.yummy.Model.Note;
import com.example.yummy.R;
import com.example.yummy.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Shopping extends Fragment{

    private NoteViewModel viewModel;
    private EditText shoppingText;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;


    public Shopping() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        shoppingText =view.findViewById(R.id.shoppingEditText);
        shoppingText.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId==EditorInfo.IME_ACTION_DONE){
                    Note note=new Note(shoppingText.getText().toString());
                    viewModel.saveNote(note);
                    shoppingText.setText("");
                    return true;
                }
                return false;
            }
        });

        recyclerView = view.findViewById(R.id.noteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getNotes();
        viewModel.notes.observe(this.getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if (notes != null) {
                    noteAdapter.setItem(notes);
                }
            }
        });
    }



}