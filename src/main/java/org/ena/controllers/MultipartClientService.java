package org.ena.controllers;

import org.ena.models.piecejointe.PieceJointe;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Path("/client")
public class MultipartClientService {

    @POST
    @Path("/save/{idpiece}")
    @Transactional
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String sendFile(byte[] photo, @PathParam("idpiece") String idpiece) throws Exception {//FileUploadForm data
        System.out.println(photo.length);
        File cr = new File("file");
        //idpiece = cr.getAbsolutePath()+File.separator+idpiece;
        Files.write(Paths.get("C:\\Users\\Public\\"+idpiece+".jpg"), photo, StandardOpenOption.CREATE);
        //FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Public\\"+idpiece+".jpg");
        //
        //outputStream.write(photo);
        //outputStream.close();
        return "Cool cool !";//service.sendMultipartData("");
    }

}
