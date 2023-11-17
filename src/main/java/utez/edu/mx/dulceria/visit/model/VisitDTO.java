package utez.edu.mx.dulceria.visit.model;
import utez.edu.mx.dulceria.statusVisit.model.Status_visit;
import utez.edu.mx.dulceria.store.model.Store;

public class VisitDTO {
    private long id;
    private String day_visit;
    private Status_visit status;
    private Store store;

    public VisitDTO() {
    }

    public VisitDTO(long id, String day_visit, Status_visit status, Store store) {
        this.id = id;
        this.day_visit = day_visit;
        this.status = status;
        this.store = store;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDay_visit() {
        return day_visit;
    }

    public void setDay_visit(String day_visit) {
        this.day_visit = day_visit;
    }

    public Status_visit getStatus() {
        return status;
    }

    public void setStatus(Status_visit status) {
        this.status = status;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store storeDTO) {
        this.store = storeDTO;
    }
}
