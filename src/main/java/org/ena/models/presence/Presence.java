package org.ena.models.presence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;

@Entity
public class Presence extends PanacheEntity {
    String idcarte;
    String lelo;
    String dateArrive;
    String dateDepart;

    public String getLelo() {
        return lelo;
    }

    public void setLelo(String lelo) {
        this.lelo = lelo;
    }

    public String getIdcarte() {
        return idcarte;
    }

    public void setIdcarte(String idcarte) {
        this.idcarte = idcarte;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }
}
