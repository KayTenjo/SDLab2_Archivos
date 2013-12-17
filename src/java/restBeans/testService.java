/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restBeans;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Kay
 */
@Stateless
@Path("/factorial")

public class testService {
    
    @GET
    public String factorial(@QueryParam("base") long base) {
        System.out.println("entre al service yey");
        return Long.toString($factorial(base));
    }
    
    private long $factorial(long base) {
        if (base >= 1) {
            return $factorial(base - 1) * base;
        }
        return 1;
    }

    
}


