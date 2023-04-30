package vistula.hm.firstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteRVAdapter extends RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder> {
    private List<Note> noteList = new ArrayList<>();
    private Context context;
    public NoteRVAdapter(List<Note> noteList,Context context){
        this.noteList=noteList;
        this.context=context;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_rv_item,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note newNote = noteList.get(position);
        holder.usernameTV.setText(newNote.getUsername());
        holder.titleTV.setText(newNote.getTitle());
        holder.descriptionTV.setText(newNote.getDescription());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private TextView usernameTV,titleTV,descriptionTV;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTV = itemView.findViewById(R.id.idTVUserName);
            titleTV = itemView.findViewById(R.id.idTVNoteTitle);
            descriptionTV = itemView.findViewById(R.id.idTVCourseDescription);

        }
    }
}
