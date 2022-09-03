package org.ena.models.presence;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ena.models.agent.Agent;
import org.ena.models.agent.AgentRepository;
import org.ena.models.eleve.Eleve;
import org.ena.models.eleve.EleveRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class PresneceRepository implements PanacheRepository<Presence> {
    @Inject
    AgentRepository agentRepository;
    //
    @Inject
    EleveRepository eleveRepository;
    //
    public List<Presence> getAllByIdForMonth(String idcarte, String mois, String annee) {
        List<Presence> l = new LinkedList<>();
        //
        listAll().forEach((e) -> {
            //System.out.println("Le mois: "+e.getDateTime().split("-")[1]+"  : "+mois);
            //System.out.println("Le mois: "+e.getDateTime().split("-")[1].equals(mois));
            System.out.println("Le id: "+e.getIdcarte()+"  : "+idcarte);
            System.out.println("Le id: "+e.getIdcarte().equals(idcarte));
            if(e.getLelo().split("-")[0].equals(annee)
                    && e.getLelo().split("-")[1].equals(mois)
                    && e.getIdcarte().equals(idcarte)){
                l.add(e);
            }
        });
        //
        return l;
    }
    public HashMap allbydate(String date) {
        List<Presence> l = new LinkedList<>();
        //
        List<HashMap<String,Object>> l1 = new LinkedList<>();
        //
        List<HashMap<String,Object>> l2 = new LinkedList<>();
        //
        listAll().forEach((e) -> {
            String dd = e.getLelo();
            LocalDate d1 = LocalDate.parse(dd);
            LocalDate d2 = LocalDate.parse(date.split(" ")[0]);

            //System.out.println("Le mois: "+e.getDateTime().split("-")[0]+"  : "+ann);
            //System.out.println("Le mois: "+e.getDateTime().split("-")[1]+"  : "+mois);
            //System.out.println("Le mois: "+e.getDateTime().split("-")[2]+"  : "+jour);
            if(d1.equals(d2)){
                l.add(e);
            }
        });
        //
        for(Presence presence : l){
            Agent agt = agentRepository.getDetails(presence.getIdcarte());
            if(agt != null){
                HashMap<String,Object> agent = new HashMap<>();
                agent.put("id",""+agt.id);
                agent.put("nom",""+agt.getNom());
                agent.put("postnom",""+agt.getPostnom());
                agent.put("prenom",""+agt.getPrenom());
                agent.put("genre",""+agt.getGenre());
                agent.put("grade",""+agt.getGrade());
                agent.put("fonction",""+agt.getFonction());
                agent.put("matricule",""+agt.getMatricule());
                //System.out.println("truc :"+presence.getDateDepart().split(" ")[1]);
                if(presence.getDateDepart() != null){
                    agent.put("dd",""+presence.getDateDepart().split(" ")[1]);
                }else{
                    agent.put("dd",null);
                }
                //
                if(presence.getDateArrive() != null){
                    agent.put("da",""+presence.getDateArrive().split(" ")[1]);
                }else{
                    agent.put("da",null);
                }
                //agent.put("da",""+presence.getDateArrive().split(" ")[1].split("."));

                l1.add(agent);
            }
            //
            Eleve ele = eleveRepository.getDetails(presence.getIdcarte());
            if(ele != null){
                HashMap<String,Object> eleve = new HashMap<>();
                eleve.put("id",""+ele.id);
                eleve.put("nom",""+ele.getNom());
                eleve.put("postnom",""+ele.getPostnom());
                eleve.put("prenom",""+ele.getPrenom());
                eleve.put("genre",""+ele.getGenre());
                eleve.put("email",""+ele.getEmail());
                eleve.put("telephone",""+ele.getTelephone());
                eleve.put("categorie",""+ele.getCategorie());
                eleve.put("dd",""+presence.getDateDepart().split(" ")[1].split(".")[0]);
                eleve.put("da",""+presence.getDateArrive().split(" ")[1].split(".")[0]);
                l2.add(eleve);
            }
            //
        }
        //
        HashMap<String,Object> map = new HashMap<>();
        map.put("listeagent", l1);
        map.put("listeeleve", l2);

        //
        return map;
    }

    public String save(Presence presence){
        LocalDate d = LocalDate.now();
        Date dh = new Date();
        Date dh1 = new Date();
        dh1.setHours(15);
        dh1.setMinutes(30);
        List<Presence> l = listAll();
        Boolean check = false;
        Long id_ = 0L;
        String dd = "";
        String le = ""+d.getYear()+"-"+d.getMonthValue()+"-"+d.getDayOfMonth()+"";
        System.out.println(le);
        LocalDate dlelo = LocalDate.parse(presence.getLelo());

        if(dlelo.equals(d)){
            Long idUtilisateur = agentRepository.getAgentId(presence.getIdcarte());
            if(idUtilisateur != null){
            for(Presence e : l){
                String lelo = ""+d.getYear()+"-"+d.getMonthValue()+"-"+d.getDayOfMonth()+"";
                System.out.println("lelo: "+lelo+"e: "+e.getLelo().equals(lelo)+":"+e.getLelo());
                //
                //
                LocalDate d1 = LocalDate.parse(e.getLelo());
                LocalDate d2 = LocalDate.now();
                //
                if(d1.equals(d2) && e.getIdcarte().equals(presence.getIdcarte())){
                    check=true;
                    id_=e.id;
                    dd=e.getDateDepart();
                }
            };
            if(check){//dh.after(dh1) ||
                System.out.println("Cool+ "+dd);
                Presence presence1 = Presence.findById(id_);
                presence1.setDateDepart(getDate());
                //
                System.out.println("id: "+presence1.id);
                System.out.println("lelo: "+presence1.getLelo());
                System.out.println("da: "+presence1.getDateArrive());
                System.out.println("dd: "+presence1.getDateDepart());
                //
                return "Sortie enregistré";
            }else{
                if(check){
                    return "Vous-avez déjà un enregistrement de la presence";
                }else {
                    System.out.println("Pas cool");
                    presence.setIdutilisateur(idUtilisateur);//
                    presence.setDateArrive(getDate());
                    presence.setDateArrive(null);
                    presence.persist();
                    return "Presence enregistré";
                }
            }}else{
                return null;
            }
        }else{
            return null;
        }
    }

    public void UpdatePresence(Long idutilisateur, String idcarte){
        listAll().forEach((e)->{
            if(e.getIdutilisateur().equals(idutilisateur)){
                e.setIdcarte(idcarte);
            }
        });
    }

    private String getDate(){
        LocalDateTime l = LocalDateTime.now();
        String mois = (l.getMonthValue()+"".length() == 1) ? "0"+l.getMonthValue():
                l.getMonthValue()+"";
        String jour = (l.getDayOfMonth()+"".length() == 1) ? "0"+l.getDayOfMonth():
                l.getDayOfMonth()+"";
        String date = l.getYear()+"-"+
                mois
                +"-"+jour+" "
                +l.getHour()+":"+l.getMinute()+":"+l.getSecond()+"."+l.getNano();
        return date;
    }
}
