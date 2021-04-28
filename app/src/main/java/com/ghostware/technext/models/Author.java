package com.ghostware.technext.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "authors")
public class Author {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String avatar;
    public String profession;

    public Author() {
    }

    public Author(int id, String name, String avatar, String profession) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.profession = profession;
    }
}
