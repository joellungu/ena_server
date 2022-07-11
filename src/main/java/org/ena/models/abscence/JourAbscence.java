package org.ena.models.abscence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class JourAbscence extends PanacheEntity {
    String motifs;
    String date;
    Long idutilisateur;

    public Long getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Long idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getMotifs() {
        return motifs;
    }

    public void setMotifs(String motifs) {
        this.motifs = motifs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
