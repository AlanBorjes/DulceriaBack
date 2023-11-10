package utez.edu.mx.dulceria.store.model;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;

import utez.edu.mx.dulceria.person.model.Person;

@Entity
public class Store {
    /*datos de la tienda:
     name, rfc, address
     dueño de la tienda :
     name, phone,  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column()
    private String name;
    @Column()
    private String address;
    @Column()
    private String rfc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

     @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliver_id", referencedColumnName = "id")
    private Person deliver;

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

    public void setOwner(Person owner) { this.owner = owner; }

    public Person getDeliver() { return deliver; }

    public void setDeliver(Person deliver) { this.deliver = deliver; }
    



    
    
}
