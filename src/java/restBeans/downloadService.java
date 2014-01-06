/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restBeans;

import entity.Archivo;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("/download")

public class downloadService {
    @PersistenceContext(unitName = "SDLab2_ArchivosPU")
    private EntityManager em;
    
    @GET
    @Path("/{user}/{file}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarArchivo(@PathParam("file") String archivo, @PathParam("user") String usuario) {
        System.out.println("Voy a descargar el archivo" + archivo);
        
        String nombre_real = selectArchivoByNombreVirtual(archivo, usuario);
        String ruta = "C:\\uploaded\\"+ nombre_real;
        File file = new File(ruta);
        
        return Response.ok(file).header("Content-Disposition", "attachment; filename=" + file.getName()).build();

        
        
    }
    
    
    public String selectArchivoByNombreVirtual(String archivo, String usuario) {
        Collection<Archivo> resultQuery;
        Collection<Archivo> result = new LinkedList<>();
        
        Query q = em.createNamedQuery("Archivo.findByArchivoUsuarioAndArchivoNombreVirtual", Archivo.class);
        q.setParameter("archivoNombreVirtual", archivo);
        q.setParameter("archivoUsuario", usuario);
        
        resultQuery = (Collection<Archivo>) q.getResultList();
        String nombre_real = null;
        for(Archivo iter: resultQuery){
            nombre_real = iter.getArchivoNombreFisico();
        }
        return nombre_real;
    }    

    public void persist(Object object) {
        em.persist(object);
    }
    
}
