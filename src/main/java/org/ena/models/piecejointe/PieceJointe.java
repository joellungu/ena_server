package org.ena.models.piecejointe;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class PieceJointe extends PanacheEntity {
    Long idpiece;
    byte[] photo;

    public Long getIdpiece() {
        return idpiece;
    }

    public void setIdpiece(Long idpiece) {
        this.idpiece = idpiece;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
