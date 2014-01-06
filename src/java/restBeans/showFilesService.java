/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restBeans;

import com.google.gson.Gson;
import entity.Archivo;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import javaClasses.listarArchivo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kay
 */

@Stateless
@Path("/list")

public class showFilesService {
    @PersistenceContext(unitName = "SDLab2_ArchivosPU")
    private EntityManager em;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarArchivos(@QueryParam("user") String usuario) {
       
        
        Collection<listarArchivo> coleccion =  selectArchivoByNombreUsuario(usuario);
        
        Gson gson = new Gson();
        String json = gson.toJson(coleccion);
        return json;
        
        
    }
    public Collection<listarArchivo> selectArchivoByNombreUsuario(String usuario) {
        Collection<Archivo> resultQuery;
        Collection<listarArchivo> result = new ArrayList<>();
        
        Query q = em.createNamedQuery("Archivo.findByArchivoUsuario", Archivo.class);
        q.setParameter("archivoUsuario", usuario);
        
        resultQuery = (Collection<Archivo>) q.getResultList();
        
        for(Archivo iter: resultQuery){
            listarArchivo temp = new listarArchivo();
            temp.setNombre_virtual(iter.getArchivoNombreVirtual());
            temp.setUrl(iter.getArchivoUrl());
            result.add(temp);
        }
        return result;
    }    

    public void persist(Object object) {
        em.persist(object);
    }
   
    }

