package org.ena.models.eleve;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ena.models.agent.Agent;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
public class EleveRepository implements PanacheRepository<Eleve> {

    public Eleve saveEleve(Eleve aleve){
        Predicate<Eleve> prediction = a -> a.getNom().equals(aleve.nom)
                && a.getPostnom().equals(aleve.postnom);

        List<Eleve> liste = listAll().stream().filter(prediction).collect(Collectors.toList());
        if(liste.isEmpty()){
            aleve.persist();//
            return aleve;
        }else{
            return null;
        }
    }

    public Eleve getDetails(String id){
        Predicate<Eleve> prediction = a -> a.getIdcarte().equals(id);
        //
        //
        try {
            Eleve e = listAll().stream().filter(prediction).collect(Collectors.toList()).stream().findFirst().get();

            return e;
        }catch (Exception ex){
            return null;
        }
    }
}
