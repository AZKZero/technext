package com.ghostware.technext.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.ArrayList;

public class BlogAuthor {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    public int id;

    public String title;
    public String description;

    public String cover_photo;

    public ArrayList<String> categories;

    @ColumnInfo(name = "author_id")
    public int authorId;

    @Relation(parentColumn = "author_id", entityColumn = "id")
    public Author author;

    public Blog getBlog() {
        return new Blog(id, title, description, cover_photo, categories, author.id);
    }
}
