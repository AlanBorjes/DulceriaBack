package utez.edu.mx.dulceria.store.model;

import utez.edu.mx.dulceria.person.model.Person;


public class StoreDTO {
    private long id;
    private String name;
    private String address;
    private String rfc;
    private Person owner;

    private Person deliver;

    public StoreDTO() {
    }

    public StoreDTO(long id, String name, String address, String rfc, Person owner, Person deliver) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rfc = rfc;
        this.owner = owner;
        this.deliver = deliver;
    }

    public StoreDTO(String name, String address, String rfc, Person owner, Person deliver) {
        this.name = name;
        this.address = address;
        this.rfc = rfc;
        this.owner = owner;
        this.deliver = deliver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Person getDeliver() {
        return deliver;
    }

    public void setDeliver(Person deliver) {
        this.deliver = deliver;
    }
}
