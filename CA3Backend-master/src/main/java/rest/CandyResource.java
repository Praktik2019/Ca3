/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Candy;
import exceptions.NotFoundException;
import facade.CandyFacade;
import fetchcandy.FetchThread;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.annotation.security.RolesAllowed;
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

/**
 * REST Web Service
 *
 * @author alber
 */
@Path("candy")
public class CandyResource {

    @Context
    private UriInfo context;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    CandyFacade candyFacade = new CandyFacade();

    /**
     * Creates a new instance of CandyResource
     */
    public CandyResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CandyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCandy() {
        return Response.ok().entity(gson.toJson(candyFacade.getAllCandy())).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)    
    public Response getCandyById(@PathParam("id") int id){
        return Response.ok().entity(gson.toJson(candyFacade.getCandyById(id))).build();
    }
    
    @POST
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCandy(String content) {
        Candy newCandy = gson.fromJson(content, Candy.class);
        System.out.println("newCandy: " + newCandy);
        candyFacade.createCandy(newCandy);
        return Response.ok().entity(gson.toJson(newCandy)).build();
    }

    /**
     * PUT method for updating or creating an instance of CandyResource
     * @param content representation for the resource
     */
    @PUT
    @RolesAllowed({"admin"})
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCandy( String content, @PathParam("id") int id) {
        Candy newCandy = gson.fromJson(content, Candy.class);
        Candy savedCandy = candyFacade.getCandyById(id);
        if(newCandy.getName()!=null)
            savedCandy.setName(newCandy.getName());
        if(newCandy.getStock()!= 0)
            savedCandy.setStock(newCandy.getStock());
        if(newCandy.getFlavour()!=null)
            savedCandy.setFlavour(newCandy.getFlavour());
        if(newCandy.getType()!=null)
            savedCandy.setType(newCandy.getType());
        if(newCandy.getImg()!=null)
            savedCandy.setImg(newCandy.getImg());
        candyFacade.updateCandy(savedCandy);
        
        return Response.ok().entity(gson.toJson(savedCandy)).build();
    }
    
    @DELETE
    @RolesAllowed({"admin"})
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCandy( String content, @PathParam("id") int id) throws NotFoundException {
        Candy deletedCandy = candyFacade.deleteCandy(id);
        
        return Response.ok().entity(gson.toJson(deletedCandy)).build();
    }
    
    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)    
    public Response getTest() throws InterruptedException, IOException, ProtocolException, ExecutionException {
        FetchThread ft = new FetchThread();
        ArrayList<URL> urls = new ArrayList();
        urls.add(new URL("https://swapi.co/api/people/1"));
        urls.add(new URL("https://swapi.co/api/planets/3"));
        return Response.ok().entity(gson.toJson(ft.fetchStuff(urls))).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
