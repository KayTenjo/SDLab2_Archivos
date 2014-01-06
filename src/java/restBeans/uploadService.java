/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restBeans;


import com.google.gson.Gson;
import com.sun.jersey.core.util.Base64;
import entity.Archivo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javaClasses.subirArchivo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Kay
 */
@Stateless
@Path("/upload")

public class uploadService {
    
    private String puerto="8080";
    
    @PersistenceContext(unitName = "SDLab2_ArchivosPU")
    private EntityManager em;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
   
    public String subirArchivo(final String input) {
      
        Gson gson = new Gson();
        subirArchivo objeto = gson.fromJson(input, subirArchivo.class);
        System.out.println("el nombre del archivo que recibi es :");
        System.out.println(objeto.getNombre_virtual());
        
        byte[] decode = Base64.decode(objeto.getArchivo());
        InputStream myInputStream = new ByteArrayInputStream(decode); 
        String uploadedFileLocation = "C:\\uploaded\\" + objeto.getNombre_real();
 
		// save it
        writeToFile(myInputStream, uploadedFileLocation);
        String url = generarURL(objeto.getUsuario(),objeto.getNombre_virtual());
        saveToBD(objeto.getUsuario(), objeto.getNombre_real(), objeto.getNombre_virtual(),url);
        
        System.out.println("Subi el archivo yey ");
		
         return "oli";

	}
 
	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {
 
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
 
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
 
			e.printStackTrace();
		}
 
	}
        
        private void saveToBD(String usuario,String nombre_real, String nombre_virtual, String url){
        
        Archivo archivo = new Archivo();
        archivo.setArchivoUsuario(usuario);
        archivo.setArchivoNombreFisico(nombre_real);
        archivo.setArchivoNombreVirtual(nombre_virtual);
        archivo.setArchivoUrl(url);
        
        persist(archivo);
        }
        
         private String generarURL(String usuario, String archivo) {
             
             String url = "http://localhost:" + puerto + "/SDLab2_Archivos/webresources/download/" + usuario + "/" +archivo;
             System.out.println(url);
             return url;
        
    }
    

    public void persist(Object object) {
        em.persist(object);
    }
}
   
     
    
    

