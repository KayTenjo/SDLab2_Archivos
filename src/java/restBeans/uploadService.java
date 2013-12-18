/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restBeans;

import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javaClasses.subirArchivo;
import javax.ejb.Stateless;
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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String subirArchivo(final String input) throws Base64DecodingException {
      
        Gson gson = new Gson();
        subirArchivo objeto = gson.fromJson(input, subirArchivo.class);
        System.out.println("el nombre del archivo que recibi es :");
        System.out.println(objeto.getNombre_virtual());
        
        byte[] decode = Base64.decode(objeto.getArchivo());
        InputStream myInputStream = new ByteArrayInputStream(decode); 
        String uploadedFileLocation = "C:\\uploaded\\" + objeto.getNombre_virtual() ;
 
		// save it
		writeToFile(myInputStream, uploadedFileLocation);
 
        
        System.out.println("Subi el archivo yey ");
		
         return input;

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
    }
     
    
    

