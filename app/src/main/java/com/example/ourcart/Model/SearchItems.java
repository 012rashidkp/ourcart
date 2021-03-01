package com.example.ourcart.Model;

public class SearchItems {
    String id;
    String prodname;

    public SearchItems(String id, String prodname) {
        this.id = id;
        this.prodname = prodname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }
}
