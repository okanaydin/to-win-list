package xyz.oguzcelik.towinlist;

import java.util.Date;

/**
 * Created by Cynapsis on 5/14/2016.
 */
public class Report {
    String name,description;
    int prize;
    String photoUrl;
    Date time;

    public Report(String name, String description, int prize, String photoUrl, Date time) {
        this.name = name;
        this.description = description;
        this.prize = prize;
        this.photoUrl = photoUrl;
        this.time = time;
    }

}
