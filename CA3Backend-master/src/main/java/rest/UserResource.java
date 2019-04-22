/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import exceptions.AuthenticationException;
import exceptions.NotFoundException;
import facade.UserFacade;
import fetchstarwars.FetchThreadSWAPI;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.ExecutionException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author Mads
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;
    
    @Context
    SecurityContext securityContext;
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    UserFacade userFacade;
    
    /**
     * Creates a new instance of PersonResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of rest.UserResource
     * @return an instance of java.lang.String
     */
    @GET
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() throws InterruptedException, IOException, ProtocolException, ExecutionException {
        FetchThreadSWAPI ft = new FetchThreadSWAPI();
        return Response.ok().entity(ft.fetchStuff()).build();
    }

    @GET
    @Path("unprotected")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnprotectedJson() throws InterruptedException, IOException, ProtocolException, ExecutionException {
        FetchThreadSWAPI ft = new FetchThreadSWAPI();
        return Response.ok().entity(ft.fetchStuff()).build();
    }
    
    @POST
    @RolesAllowed({"user", "admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUser(String content) {
        User newUser = gson.fromJson(content, User.class);
        System.out.println("newUser: " + newUser);
        userFacade.createUser(newUser);
        return Response.ok().entity(gson.toJson(newUser)).build();
    }
    
    @PUT
    @Path("/{username}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser( String content, @PathParam("username") String username, @PathParam("password") String password) throws NotFoundException, AuthenticationException {
        User newUser = gson.fromJson(content, User.class);
        User savedUser = userFacade.getVeryfiedUser(username,password);
        if(newUser.getUserName()!=null)
            savedUser.setUserName(newUser.getUserName());
        if(newUser.getUserPass()!=null)
            savedUser.setUserPass(newUser.getUserPass());
        userFacade.updateUser(savedUser);
        
        return Response.ok().entity(gson.toJson(savedUser)).build();
    }
    /**
     * PUT method for updating or creating an instance of UserResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
