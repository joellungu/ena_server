package org.ena.models.abscence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class JourAbscenceRepository  implements PanacheRepository<JourAbscence> {

    public List<JourAbscence> getAllJourAbscence(String annee, String mois) {
        List<JourAbscence> l1 = listAll();
        List<JourAbscence> l = new LinkedList<>();
        l1.forEach((jourAbscence -> {
            String[] d = jourAbscence.getDate().split("-");
            if(d[0].equals(annee) && d[1].equals(mois)){
                l.add(jourAbscence);
            }
        }));
        return l;
    }
}
