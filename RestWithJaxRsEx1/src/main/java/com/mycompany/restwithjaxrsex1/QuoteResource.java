/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restwithjaxrsex1;

import CustomExceptions.QuoteNotFound;
import CustomExceptions.QuoteNotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import CustomExceptions.QuoteNotFoundExceptionMapper;

/**
 * REST Web Service
 *
 * @author Dell
 */
@Path("quote")
public class QuoteResource
{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QuoteResource
     */
    
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static int nextId = 3;
    private int quote;
    private String quoteText;
    
    
    
    
    private static Map<Integer,String> quotes = new HashMap() 
    {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };
    public QuoteResource(){};
    
    public QuoteResource(String quo)
    {
        this.quoteText = quo;
    }
    
    public QuoteResource(int key)
    {
        this.quote = key;
        this.quoteText = quotes.get(key);
    }
   
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonQuote(@PathParam("id")int id)
    {
        if (!quotes.containsKey(id))
          {
            throw new QuoteNotFoundException(new QuoteNotFound(404 , "Quote with id not found"));
          }
        
        QuoteResource q = new QuoteResource(id);
        return Response.ok().entity(gson.toJson(q)).type(MediaType.APPLICATION_JSON).build();
        
        
    }
    
    @GET
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomQuote()
    {
        Random rand = new Random();
        int  n = rand.nextInt(10) + 1;
      
        if (!quotes.containsKey(n))
          {
            throw new QuoteNotFoundException(new QuoteNotFound(404 , "Quote with id not found"));
          }
        QuoteResource q = new QuoteResource(n);
        return Response.ok().entity(gson.toJson(q)).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postQuote(String quote)
    {
        nextId++;
        quotes.put(nextId, quote);
        QuoteResource q = new QuoteResource(nextId);
        return gson.toJson(q);
    }
    
    
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteQuote(@PathParam("id")int id)
    {
        QuoteResource q = new QuoteResource(id);
        quotes.remove(id);
        return gson.toJson(q);       
    }
    
    
    
    
    
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml()
    {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of QuoteResource
     * @param content representation for the resource
     */
    
    @PUT
    @Path("put/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putXml(@PathParam("id") int id , String quote)
    {
        
        if (!quotes.containsKey(id))
          {
            throw new QuoteNotFoundException(new QuoteNotFound(404 , "Quote with id not found"));
          }
        quotes.put(id, quote);
        QuoteResource q = new QuoteResource(id);
        return Response.ok().entity(gson.toJson(q)).type(MediaType.APPLICATION_JSON).build();
    }


}
