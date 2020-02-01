package com.depo.ws;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ejb.EJB;

import com.entities.Usuario;
import com.exception.ServicesException;
import com.services.UsuarioBeanRemote;

@Path("/autenticar")
public class FamiliasRest {

    @EJB
    private UsuarioBeanRemote usuarioBean;

    @POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response autenticar(String nombreAcceso, String contrasena){
		try {
		usuarioBean.autenticar(nombreAcceso, contrasena);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
}
