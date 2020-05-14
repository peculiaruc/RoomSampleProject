package com.peculiaruc.roomsampleproject.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.peculiaruc.roomsampleproject.Note;
import com.peculiaruc.roomsampleproject.R;
import com.peculiaruc.roomsampleproject.ui.MainActivity;
import com.peculiaruc.roomsampleproject.ui.editnote.EditNoteActivity;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private OnDeleteClickListener onDeleteClickListener;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<Note> mNotes;

    public NoteListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;

    }

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Note myNote);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemview = layoutInflater.inflate(R.layout.item_list_note, parent, false);
        NoteViewHolder viewHolder =  new NoteViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        if (mNotes != null){
            Note note = mNotes.get(position);
            holder.setDate(note.getNote(), position);
            holder.setListener();
        }else{
            holder.noteItemView.setText(R.string.no_note);
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
        return mNotes.size();
        else return 0;
    }
    public void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView noteItemView;
        private int mPostion;
        private ImageView imgdelete, imgedit;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.textView_note);
            imgdelete    = itemView.findViewById(R.id.img_delete);
            imgedit      = itemView.findViewById(R.id.img_edit);
        }

        public void setDate(String note, int position) {
            noteItemView.setText(note);
            mPostion = position;
        }

        public void setListener() {
            imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, EditNoteActivity.class);
                    intent.putExtra("note_id", mNotes.get(mPostion).getId());
                    ((Activity)mContext).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST__CODE_);
                }
            });
            imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(mNotes.get(mPostion));
                    }
                }
            });
        }
    }
}
