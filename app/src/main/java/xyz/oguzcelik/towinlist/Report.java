package xyz.oguzcelik.towinlist;

import java.io.Serializable;

/**
 * Created by Cynapsis on 5/14/2016.
 */
public class Report implements Serializable {
    static final long serialVersionUID = 1L;

    String name,description;
    long prize;
    String photoUrl;

    public Report(String name, String description, long prize, String photoUrl) {
        this.name = name;
        this.description = description;
        this.prize = prize;
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
