package org.ena.models.agent;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
public class AgentRepository implements PanacheRepository<Agent> {

    public Agent saveAgent(Agent agent){
        Predicate<Agent> prediction = a -> a.getMatricule().equals(agent.matricule)
                && a.getNom().equals(agent.nom)
                && a.getPostnom().equals(agent.postnom);

        List<Agent> liste = listAll().stream().filter(prediction).collect(Collectors.toList());
        if(liste.isEmpty()){
            agent.persist();//
            return agent;
        }else{
            return null;
        }
    }

    public List<Agent> getAllByName(String nom){
        Predicate<Agent> prediction = a -> a.getNom().toLowerCase().contains(nom.toLowerCase()) ||
                a.getPostnom().toLowerCase().contains(nom.toLowerCase());
        //
        List<Agent> liste = listAll().stream().filter(prediction).collect(Collectors.toList());
        return liste;
    }

    public Agent getDetails(String id){
        System.out.println("Le id: "+id);
        Predicate<Agent> prediction = a -> a.getIdcarte().equals(id);
        Agent agent = new Agent();
        //
        try {
            for (Agent ag : listAll()) {
                if (ag.idcarte.equals(id)) {
                    agent = ag;
                    break;
                }
            }
            if (agent.idcarte != null) {
                //Agent a = listAll().stream().filter(prediction).collect(Collectors.toList()).stream().findFirst().get();

                return agent;
            } else {
                return null;
            }
        }catch (Exception ex){
            return null;
        }
    }
    public Agent getAgentByMatricule(String matricule){
        System.out.println("Le id: "+matricule);
        //Predicate<Agent> prediction = a -> a.getMatricule().equals(matricule);
        Agent agent = new Agent();
        //
        try {
            for (Agent ag : listAll()) {
                if (ag.idcarte.equals(matricule)) {
                    agent = ag;
                    break;
                }
            }
            if (agent.idcarte != null) {
                //Agent a = listAll().stream().filter(prediction).collect(Collectors.toList()).stream().findFirst().get();

                return agent;
            } else {
                return null;
            }
        }catch (Exception ex){
            return null;
        }
    }
    public Long getAgentId(String id){
        System.out.println("Le id: "+id);
        Predicate<Agent> prediction = a -> a.getIdcarte().equals(id);
        Agent agent = new Agent();
        //
        try {
            for (Agent ag : listAll()) {
                if (ag.idcarte.equals(id)) {
                    agent = ag;
                    break;
                }
            }
            if (agent.idcarte != null) {
                //Agent a = listAll().stream().filter(prediction).collect(Collectors.toList()).stream().findFirst().get();

                return agent.id;
            } else {
                return null;
            }
        }catch (Exception ex){
            return null;
        }
    }

}
