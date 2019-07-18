package com.example.todoappwithfirebase;

import com.google.firebase.database.IgnoreExtraProperties;

public class Nodes {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String id;
        public String title;
        public String description;

        public Nodes() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Nodes(String id, String title ,String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

    }

