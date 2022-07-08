package org.ena.models.abscence;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ena.models.agent.Agent;
import org.ena.models.presence.Presence;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
public class AbscenceRepository implements PanacheRepository<Abscence> {
    //
    public List<Abscence> getAllByIdForMonth(Long id, String mois) {
        List<Abscence> l = new LinkedList<>();
        //
        listAll().forEach((e) -> {
            System.out.println("Le mois: "+e.getDateDebut().split("-")[1]+"  : "+mois);
            System.out.println("Le mois: "+e.getDateDebut().split("-")[1].equals(mois));
            System.out.println("Le id: "+e.getIdutilisateur()+"  : "+id);
            System.out.println("Le id: "+e.getIdutilisateur().equals(id));
          //  if(e.getId().equals(id) && e.getDateDebut().split("-")[1].equals(mois)){
                l.add(e);
           // }
        });
        //
        return l;
    }

}
