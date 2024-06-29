// card.java
package com.example.agrimony;

import java.util.Map;

public class card {
    private String title;
    private String date;
    private Map<String, String> description;
    private String imageUri;
    private String documentId;

    public card(String title, Map<String, String> description, String imageUri, String documentId, String date) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.imageUri = imageUri;
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getDocumentId() {
        return documentId;
    }
}
