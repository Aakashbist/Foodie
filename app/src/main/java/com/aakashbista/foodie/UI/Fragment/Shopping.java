package com.aakashbista.foodie.UI.Fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aakashbista.foodie.Adapter.NoteAdapter;
import com.aakashbista.foodie.Model.Note;
import com.aakashbista.foodie.R;
import com.aakashbista.foodie.ViewModel.NoteViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Shopping extends Fragment {

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
        shoppingText = view.findViewById(R.id.shoppingEditText);
        shoppingText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!shoppingText.getText().toString().isEmpty()) {
                        Note note = new Note(shoppingText.getText().toString());
                        viewModel.saveNote(note);
                        shoppingText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        recyclerView = view.findViewById(R.id.noteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(swipeCallback);
        touchHelper.attachToRecyclerView(recyclerView);

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

        viewModel.notes.observe(this.getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if (notes != null) {
                    noteAdapter.setItem(notes);
                }
            }
        });
    }

    ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {


        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            viewModel.deleteNote(viewHolder.getAdapterPosition());
           Snackbar.make(getView(), "Deleted", Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder, float dX,
                                float dY,int actionState, boolean isCurrentlyActive) {

            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                // Get RecyclerView item from the ViewHolder
                View itemView = viewHolder.itemView;
                Drawable icon;

                Paint paint = new Paint();
                if (dX <0) {
                    icon = ContextCompat.getDrawable(getContext(), R.drawable.ic_delete_black);
                    paint.setColor(Color.parseColor("#D32F2F"));
                    icon.setTint(Color.WHITE);
                    int top = (itemView.getHeight() - icon.getIntrinsicHeight());
                    int left= itemView.getWidth() - icon.getIntrinsicWidth() - top;
                    c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                        (float) itemView.getRight(), (float) itemView.getBottom(), paint);

                    icon.setBounds(left, top, left + icon.getIntrinsicWidth(), top + icon.getIntrinsicHeight());
                    icon.draw(c);
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
        private int convertDpToPx(int dp){
            return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
        }
    };


}