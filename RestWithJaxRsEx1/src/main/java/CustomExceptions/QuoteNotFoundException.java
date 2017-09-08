/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomExceptions;

import javax.ws.rs.ext.Provider;

/**
 *
 * @author Dell
 */
public class QuoteNotFoundException extends RuntimeException
{
    public QuoteNotFound q;

    /**
     * Creates a new instance of <code>NotFoundException</code> without detail
     * message.
     */
    public QuoteNotFoundException()
    {
        
    }

    /**
     * Constructs an instance of <code>NotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public QuoteNotFoundException(String msg)
    {
        super(msg);
    
    }
   
    public QuoteNotFoundException (QuoteNotFound q)
    {
        this.q = q;
        
    }
    
}
