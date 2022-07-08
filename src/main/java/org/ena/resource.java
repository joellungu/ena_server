package org.ena;

import org.ena.models.agent.Agent;
import org.ena.models.agent.AgentRepository;
import org.ena.models.presence.Presence;
import org.ena.models.presence.PresneceRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Path("control")
public class resource {

    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        controlleur();
        return "Salut ro!";
    }

    public void controlleur(){
        TimerTask tacheDeControle = new TimerTask() {

            @Inject
            AgentRepository agentRepository;

            @Inject
            PresneceRepository presneceRepository;
            LocalDate d = LocalDate.now();
            String le = ""+d.getYear()+"-"+d.getMonthValue()+"-"+d.getDayOfMonth()+"";
            @Override
            public void run() {

                List<Agent> listeAgent = agentRepository.listAll();
                listeAgent.forEach((e)->{
                    Predicate<Presence> pred = r->r.getIdcarte().equals(e.getIdcarte()) && r.getLelo().equals(le);

                    if(presneceRepository.listAll().stream().filter(pred)
                            .collect(Collectors.toList()).isEmpty()){
                        Presence p = new Presence();
                        p.setPresent(false);
                        p.setIdcarte(e.getIdcarte());
                        p.setDateDepart(le);
                        p.setLelo(le);
                        p.setDateArrive(le);
                        presneceRepository.persist(p);
                    }
                });

                System.out.println("Une tache qui vient tout le: "+ System.currentTimeMillis());
            }
        };
        //
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 47);
        calendar.set(Calendar.SECOND, 0);
        Date temps = calendar.getTime();


        Timer timer = new Timer("Temps");
        long delay = 1000L * 5;
        long periode = 1000L * 10;
        timer.schedule(tacheDeControle,temps);
    }
}