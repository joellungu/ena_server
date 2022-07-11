package org.ena.controllers;


import org.ena.models.abscence.Abscence;
import org.ena.models.abscence.AbscenceRepository;
import org.ena.models.abscence.JourAbscence;
import org.ena.models.abscence.JourAbscenceRepository;
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

    @Inject
    JourAbscenceRepository jourAbscenceRepository;

    @Path("save")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Abscence saves(Abscence abscence){
        abscence.persist();
        return abscence;
    }

    @Path("savejourabscent")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveJourAbscence(JourAbscence jourAbscence){
        jourAbscence.persist();
    }

    @Path("alljourabscent/{annee}/{mois}")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<JourAbscence> allJourAbscence(@PathParam("annee") String annee, @PathParam("mois") String mois){
        return jourAbscenceRepository.getAllJourAbscence(annee, mois);
    }

    @Path("alljourabscent")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<JourAbscence> allJourAbscences(){
        return jourAbscenceRepository.listAll();
    }

    @Path("all/{id}/{mois}/{annee}")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Abscence> allByIdMonth1(@PathParam("id") Long id, @PathParam("mois") String mois, @PathParam("annee") String annee){
        System.out.println("le contenu: "+id+"::"+mois+"::"+annee);
        return abscenceRepository.getAllByIdForMonth(id,mois,annee);
    }

    @Path("alljourabscent/{id}/{mois}/{annee}")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<JourAbscence> allByIdMonth2(@PathParam("id") Long id,
                                       @PathParam("mois") String mois,
                                       @PathParam("annee") String annee){
        return abscenceRepository.getAllJourAbscence(id,mois,annee);
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
