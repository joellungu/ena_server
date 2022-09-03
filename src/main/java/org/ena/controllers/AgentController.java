package org.ena.controllers;

import org.ena.models.abscence.AbscenceRepository;
import org.ena.models.abscence.JourAbscenceRepository;
import org.ena.models.agent.Agent;
import org.ena.models.agent.AgentRepository;
import org.ena.models.presence.PresneceRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("agent")
public class AgentController {

    @Inject
    AgentRepository agentRepository;

    @Inject
    PresneceRepository presneceRepository;

    @Inject
    AbscenceRepository abscenceRepository;

    @Inject
    JourAbscenceRepository jourAbscenceRepository;
    //
    @Path("save")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Agent save(Agent agent){
        ///Agent a = agentRepository.saveAgent(agent);
        return agentRepository.saveAgent(agent);
    }

    @Path("update")
    @PUT
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Agent update(Agent agent){
        //
        //
        Agent oldAgent= (Agent) Agent.findById(agent.id);
        if(oldAgent == null){ 
            return null;
        }
        oldAgent.setNom(agent.getNom());
        oldAgent.setPostnom(agent.getPostnom());
        oldAgent.setGenre(agent.getGenre());
        oldAgent.setNom(agent.getNom());

        oldAgent.setPrenom(agent.getPrenom());
        oldAgent.setAdresse(agent.getAdresse());
        oldAgent.setEmail(agent.getEmail());

        oldAgent.setFonction(agent.getFonction());
        oldAgent.setMatricule(agent.getMatricule());
        oldAgent.setTelephone(agent.getTelephone());
        oldAgent.setIdcarte(agent.getIdcarte());
        oldAgent.setNiveauEtude(agent.getNiveauEtude());
        oldAgent.setGrade(agent.getGrade());
        //
        presneceRepository.UpdatePresence(oldAgent.id, agent.getIdcarte());
        //
        return oldAgent;
    }

    @Path("supprimer/{id}")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void supprimer(@PathParam("id") Long id){
        Agent.deleteById(id);
        //return agent;
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Agent get(@PathParam("id") Long id){
        Agent agent = Agent.findById(id);
        return agent;
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Agent> allAgents(){
        List<Agent> agents = Agent.listAll();
        return agents;
    }

    @Path("all/{region}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Agent> alls(@PathParam("region") String region){
        List<Agent> ags = new LinkedList<>();
        return ags;
    }

    @Path("details/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Agent details(@PathParam("id") String id){
        return agentRepository.getDetails(id);
    }

    @Path("agent/{matricule}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Agent getAgentByMatricule(@PathParam("matricule") String matricule){
        return agentRepository.getAgentByMatricule(matricule);
    }

    @Path("allbyname/{nom}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Agent> allbyname(@PathParam("nom") String nom){
        List<Agent> agens = agentRepository.getAllByName(nom);
        return agens;
    }
/*
quarkus.http.port=${PORT:8080}
quarkus.http.host=0.0.0.0
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
#quarkus.datasource.username=rqznsdkdvypsqz
  #postgres
  #jcfxvhcsoqrbmb
quarkus.datasource.password=joellungu
#quarkus.datasource.password=891d0823a6e2e289a93347032867e30cd79de4886822c6746d8ac514ac0b67ae
  #joellungu
  #891d0823a6e2e289a93347032867e30cd79de4886822c6746d8ac514ac0b67ae

#quarkus.datasource.jdbc.url=jdbc:postgresql://ec2-52-206-182-219.compute-1.amazonaws.com:5432/d66uual12bv7mr
#quarkus.datasource.jdbc.url=jdbc:postgresql://ec2-52-206-182-219.compute-1.amazonaws.com:5432/d66uual12bv7mr
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/presence
  #jdbc:postgresql://ec2-52-206-182-219.compute-1.amazonaws.com:5432/d66uual12bv7mr
#quarkus.datasource.jdbc.max-size=2
quarkus.http.limits.max-body-size=102400K

# drop and create the database at startup (use `drop-and-create --update` to only update the schema)
quarkus.hibernate-orm.database.generation = update

quarkus.rest-client."org.epst.beans.MultipartService".url=http://localhost:8080/
 */
}
