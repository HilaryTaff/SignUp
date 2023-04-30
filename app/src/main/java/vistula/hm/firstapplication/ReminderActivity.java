package vistula.hm.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {
    LinearLayout linearLayoutNotes;
    List<Button> buttonList;
    private List<Note> notesAdded;
    private DBHelper dbHelper;
    private NoteRVAdapter noteRVAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        notesAdded=new ArrayList<>();
        dbHelper= new DBHelper(this);
        notesAdded=dbHelper.recallNotes();
        noteRVAdapter=new NoteRVAdapter(notesAdded,this);
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteRVAdapter);
    }

    public void toMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(getApplicationContext(),"Saving changes",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}