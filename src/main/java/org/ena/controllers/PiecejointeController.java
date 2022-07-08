package org.ena.controllers;

import org.ena.models.piecejointe.PieceJointe;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("piecejointe")
public class PiecejointeController {

    @Path("save/{idpiece}")
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public void saves(@PathParam("idpiece") Long idpiece, byte[] photo) throws IOException {
        //
        System.out.println(photo.length);
        File cr = new File("file");
        //idpiece = cr.getAbsolutePath()+File.separator+idpiece;
        Files.write(Paths.get("C:\\Users\\Public\\"+idpiece+".jpg"), photo, StandardOpenOption.CREATE);
        //PieceJointe piecejointe = new PieceJointe();
        //piecejointe.setIdpiece(idpiece);
        //piecejointe.setPhoto(photo);
        //piecejointe.persist();
    }

    @Path("photo/{idpiece}")
    @GET
    //@Transactional
    @Produces("image/jpeg")
    //@Consumes(MediaType.APPLICATION_JSON)
    public FileInputStream allByIdMonth(@PathParam("idpiece") String idpiece) throws FileNotFoundException {/*
        AtomicReference<PieceJointe> photo = new AtomicReference<>(new PieceJointe());
        List<PieceJointe> l = PieceJointe.listAll();
        l.forEach((e)->{
            System.out.println(e.getIdpiece());
            if(e.getIdpiece().equals(idpiece)){
                System.out.println("cool mec");
                photo.set(e);
            }
        });
        return photo.get().getPhoto();
        */
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Public\\"+idpiece+".jpg"));
        return fileInputStream;
    }
}
