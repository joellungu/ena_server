package org.ena.controllers;

import org.ena.models.agent.Agent;
import org.ena.models.presence.Presence;
import org.ena.models.presence.PresneceRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("presence")
public class PresenceController {

    @Inject
    PresneceRepository presneceRepository;

    @Path("save")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Presence saves(Presence presence){
        return presneceRepository.save(presence);
    }

    @Path("all/{id}/{mois}")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Presence> allByIdMonth(@PathParam("id") String id, @PathParam("mois") String mois){
        return presneceRepository.getAllByIdForMonth(id,mois);
    }

    @Path("detailsmois")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Presence save(Presence presence){
        presence.persist();
        return presence;
    }

    @Path("allbydate/{ann}/{mois}/{jour}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Presence> allbydate(@PathParam("ann") String ann,@PathParam("mois") String mois,
                                    @PathParam("jour") String jour){
        return presneceRepository.allbydate(ann,mois,jour);
    }

    @Path("allss")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Presence> allss(){
        return presneceRepository.listAll();
    }
}
