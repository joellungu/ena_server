package org.ena.controllers;


import org.ena.models.abscence.Abscence;
import org.ena.models.abscence.AbscenceRepository;
import org.ena.models.presence.Presence;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("abscence")
public class AbscenceController {

    @Inject
    AbscenceRepository abscenceRepository;

    @Path("save")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Abscence saves(Abscence abscence){
        abscence.persist();
        return abscence;
    }

    @Path("all/{id}/{mois}")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Abscence> allByIdMonth(@PathParam("id") Long id, @PathParam("mois") String mois){
        return abscenceRepository.getAllByIdForMonth(id,mois);
    }

    @Path("all")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Abscence> all(){
        return abscenceRepository.listAll();
    }

    @Path("supprimer/{id}")
    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void supprimer(@PathParam("id") Long id){
        abscenceRepository.deleteById(id);
    }

}
