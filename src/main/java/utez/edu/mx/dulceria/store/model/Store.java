package utez.edu.mx.dulceria.store.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.visit.model.Visit;

import java.util.List;

@Entity
public class Store {
    /*datos de la tienda:
     name, rfc, address
     dueño de la tienda :
     name, phone,  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String rfc;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private Person owner;

    @ManyToOne()
    @JoinColumn(name = "deliver_id")
    private Person deliver;

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private List<Visit> visitList;

    public Store() {
    }

    public Store(long id, String name, String address, String rfc, Person owner, Person deliver) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rfc = rfc;
        this.owner = owner;
        this.deliver = deliver;
                
    }

    public Store(String name, String address, String rfc, Person owner, Person deliver) {
        this.name = name;
        this.address = address;
        this.rfc = rfc;
         this.owner = owner;
        this.deliver = deliver;
    }

    public Store(long id, String name, String address, String rfc) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rfc = rfc;
    }

    public Store(long id, String name, String address, String rfc, Person owner, Person deliver, List<Visit> visitList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rfc = rfc;
        this.owner = owner;
        this.deliver = deliver;
        this.visitList = visitList;
    }

    public List<Visit> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getRfc() { return rfc; }

    public void setRfc(String rfc) { this.rfc = rfc; }

    public Person getOwner() { return owner; }

    public void setOwner(Person owner) { this.owner = owner;}

    public Person getDeliver() { return deliver; }

    public void setDeliver(Person deliver) { this.deliver = deliver;}
    
}
