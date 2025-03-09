package com.example.filmfragments.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Film {
    @DatabaseField(columnName = "id", generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "title", canBeNull = false)
    private String title;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Genre genre;
    @DatabaseField(columnName = "description")
    private String description;
    @DatabaseField(columnName = "year")
    private int year;

    public Film(){}

    public Film(String title, Genre genre, int year, String description) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre.getName();
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
