package org.ena.models.presence;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ena.models.eleve.Eleve;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class PresneceRepository implements PanacheRepository<Presence> {
    public List<Presence> getAllByIdForMonth(String idcarte, String mois) {
        List<Presence> l = new LinkedList<>();
        //
        listAll().forEach((e) -> {
            //System.out.println("Le mois: "+e.getDateTime().split("-")[1]+"  : "+mois);
            //System.out.println("Le mois: "+e.getDateTime().split("-")[1].equals(mois));
            System.out.println("Le id: "+e.getIdcarte()+"  : "+idcarte);
            System.out.println("Le id: "+e.getIdcarte().equals(idcarte));
            if(e.getDateArrive().split("-")[1].equals(mois) && e.getIdcarte().equals(idcarte)){
                l.add(e);
            }
        });
        //
        return l;
    }
    public List<Presence> allbydate(String ann, String mois, String jour) {
        List<Presence> l = new LinkedList<>();
        //
        listAll().forEach((e) -> {
            String dd = e.getDateArrive().split(" ")[0];
            Boolean bAnn = dd.split("-")[0].equals(ann);
            Boolean bMois = dd.split("-")[1].equals(mois);
            Boolean bJour = dd.split("-")[2].equals(jour);

            //System.out.println("Le mois: "+e.getDateTime().split("-")[0]+"  : "+ann);
            //System.out.println("Le mois: "+e.getDateTime().split("-")[1]+"  : "+mois);
            //System.out.println("Le mois: "+e.getDateTime().split("-")[2]+"  : "+jour);
            if(bAnn && bMois && bJour){
                l.add(e);
            }
        });
        //
        return l;
    }

    public String save(Presence presence){
        LocalDate d = LocalDate.now();
        Date dh = new Date();
        Date dh1 = new Date();
        dh1.setHours(15);
        dh1.setMinutes(30);
        List<Presence> l = listAll();
        AtomicReference<Boolean> check = new AtomicReference<>(false);
        AtomicReference<Long> id_ = new AtomicReference<>(0L);
        AtomicReference<String> dd = new AtomicReference<>("");
        String le = ""+d.getYear()+"-"+d.getMonthValue()+"-"+d.getDayOfMonth()+"";

        if(presence.getLelo().equals(le)){
            l.forEach((e)->{
                String lelo = ""+d.getYear()+"-"+d.getMonthValue()+"-"+d.getDayOfMonth()+"";
                System.out.println("lelo: "+lelo+"e: "+e.getLelo().equals(lelo)+":"+e.getLelo());
                //
                if(e.getLelo().equals(lelo) && e.getIdcarte().equals(presence.getIdcarte())){
                    check.set(true);
                    id_.set(e.id);
                    dd.set(e.getDateDepart());
                }
            });
            if(check.get()){//dh.after(dh1) ||
                System.out.println("Cool+ "+dd.get());
                Presence presence1 = Presence.findById(id_.get());
                presence1.setDateDepart(presence.getDateDepart());
                //
                System.out.println("id: "+presence1.id);
                System.out.println("lelo: "+presence1.getLelo());
                System.out.println("da: "+presence1.getDateArrive());
                System.out.println("dd: "+presence1.getDateDepart());
                //
                return "Sortie enregistré";
            }else{
                if(check.get()){
                    return "Vous-avez déjà un enregistrement de la presence";
                }else {
                    System.out.println("Pas cool");
                    presence.persist();
                    return "Presence enregistré";
                }
            }
        }else{
            return null;
        }
    }
}
