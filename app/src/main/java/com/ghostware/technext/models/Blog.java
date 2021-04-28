package com.ghostware.technext.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "blogs")
public class Blog {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    public long id;

    public String title;
    public String description;

    @ColumnInfo(name = "cover_photo")
    public String coverPhoto;

    public ArrayList<String> categories;

    @ColumnInfo(name = "author_id")
    public long authorId;

    public Blog() {}

    public Blog(long id, String title, String description, String coverPhoto, ArrayList<String> categories, long authorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coverPhoto = coverPhoto;
        this.categories = categories;
        this.authorId = authorId;
    }
}
