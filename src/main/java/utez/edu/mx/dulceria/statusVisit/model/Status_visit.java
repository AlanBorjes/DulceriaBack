package utez.edu.mx.dulceria.statusVisit.model;


import utez.edu.mx.dulceria.visit.model.Visit;

import javax.persistence.*;
import java.util.List;

@Entity
public class Status_visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String desciprtion;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Visit> visitList;

    public Status_visit() {
    }

    public Status_visit(long id, String desciprtion) {
        this.id = id;
        this.desciprtion = desciprtion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesciprtion() {
        return desciprtion;
    }

    public void setDesciprtion(String desciprtion) {
        this.desciprtion = desciprtion;
    }


}
