package vistula.hm.firstapplication;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Note {
    private String title;
    private String description;
    private String username;
    Note(String title,String description,String username){
        this.setTitle(title);
        this.setDescription(description);
        this.setUsername(username);
    }

    private void setUsername(String username) {
        this.username=username;
    }
    public String getUsername(){
        return username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
