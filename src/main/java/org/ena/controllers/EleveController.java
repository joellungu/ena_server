package org.ena.controllers;

import org.ena.models.agent.Agent;
import org.ena.models.eleve.Eleve;
import org.ena.models.eleve.EleveRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("eleve")
public class EleveController {

    @Inject
    EleveRepository eleveRepository;
    //
    @Path("save")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Eleve save(Eleve eleve){
        //eleve.persist();
        return eleveRepository.saveEleve(eleve);
    }

    @Path("update")
    @PUT
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Eleve update(Eleve eleve){
        Eleve oldEleve = Eleve.findById(eleve.id);
        if(oldEleve == null){ 
            return null;
        }
        oldEleve.setNom(eleve.getNom());
        oldEleve.setPostnom(eleve.getPostnom());
        oldEleve.setGenre(eleve.getGenre());
        oldEleve.setNiveauEtude(eleve.getNom());
        oldEleve.setCategorie(eleve.getCategorie());
        oldEleve.setFiliere(eleve.getFiliere());
        oldEleve.setEmail(eleve.getEmail());
        oldEleve.setIdcarte(eleve.getIdcarte());
        oldEleve.setTelephone(eleve.getTelephone());
        oldEleve.setPromotion(eleve.getPromotion());
        oldEleve.setNiveauEtude(eleve.getNiveauEtude());
        return oldEleve;
    }

    @Path("supprimer/{id}")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void supprimer(@PathParam("id") Long id){
        Eleve.deleteById(id);
        //return Eleve;
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Eleve get(@PathParam("id") Long id){
        Eleve eleve = Eleve.findById(id);
        return eleve;
    }

    @Path("details/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Eleve details(@PathParam("id") String id){
        return eleveRepository.getDetails(id);
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Eleve> alls(){
        List<Eleve> eleves = Eleve.listAll();
        return eleves;
    }



}
