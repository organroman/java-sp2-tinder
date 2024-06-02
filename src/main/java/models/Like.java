package models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class Like {
    private int id;
    private int id_who;
    private int id_whom;
    private String action;

    public Like(int id_who, int id_whom, String action) {
        this.id_who = id_who;
        this.id_whom = id_whom;
        this.action = action;
    }
}
