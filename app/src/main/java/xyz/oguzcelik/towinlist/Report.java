package xyz.oguzcelik.towinlist;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cynapsis on 5/14/2016.
 */
public class Report implements Serializable {
    String name,description;
    int prize;
    String photoUrl;

    public Report(String name, String description, int prize, String photoUrl) {
        this.name = name;
        this.description = description;
        this.prize = prize;
        this.photoUrl = photoUrl;
    }

}
