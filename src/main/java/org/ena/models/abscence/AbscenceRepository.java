package org.ena.models.abscence;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ena.models.agent.Agent;
import org.ena.models.presence.Presence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
public class AbscenceRepository implements PanacheRepository<Abscence> {
    //
    @Inject
    JourAbscenceRepository jourAbscenceRepository;
    public List<Abscence> getAllByIdForMonth(Long id, String mois, String annee) {
        List<Abscence> l = new LinkedList<>();
        //
        listAll().forEach((e) -> {
                //System.out.println("Le mois: "+e.getDateTime().split("-")[1]+"  : "+mois);
                //System.out.println("Le mois: "+e.getDateTime().split("-")[1].equals(mois));
            System.out.println("Le contenu: "+e.getDateDebut());
            System.out.println("Le contenu: "+e.getIdutilisateur());
            System.out.println(e.getDateDebut().split(" ")[0].split("-")[0]+"::"+annee);
            System.out.println(e.getDateDebut().split(" ")[0].split("-")[1]+"::"+mois);

            System.out.println("Le vrai ou faut: "+e.getIdutilisateur().equals(id));

            if(e.getIdutilisateur().equals(id) &&
                        e.getDateDebut().split(" ")[0].split("-")[0].equals(annee)
                &&         e.getDateDebut().split(" ")[0].split("-")[1].equals(mois)
                ){
                    l.add(e);
                }
        });
        //
        return l;
    }

    public List<JourAbscence> getAllJourAbscence(Long id, String mois, String annee) {
        List<JourAbscence> l = new LinkedList<>();
        //
        jourAbscenceRepository.listAll().forEach((e) -> {
            //System.out.println("Le mois: "+e.getDateTime().split("-")[1]+"  : "+mois);
            //System.out.println("Le mois: "+e.getDateTime().split("-")[1].equals(mois));
            if(e.getDate().split(" ")[0].split("-")[0].equals(annee)
                    && e.getDate().split(" ")[0].split("-")[1].equals(mois)
                    && e.getIdutilisateur().equals(id)
            ){
                l.add(e);
            }
        });
        //
        return l;
    }

}
