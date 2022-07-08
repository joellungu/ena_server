package org.ena.controllers;

import org.ena.models.agent.Agent;
import org.ena.models.agent.AgentRepository;

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

    @Path("allbyname/{nom}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Agent> allbyname(@PathParam("nom") String nom){
        List<Agent> agens = agentRepository.getAllByName(nom);
        return agens;
    }

}
