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

import com.entities.Familia;
import com.exception.ServicesException;
import com.services.FamiliaBeanRemote;

@Path("/familias")
public class FamiliasRest {
	
	@EJB
	private FamiliaBeanRemote familiaBean;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFamilias(){
		return Response.ok(familiaBean.obtenerTodos()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFamiliaById(@PathParam("id") Long id) {
		try {
			familiaBean.obtenerPorId(id);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe una familia con el id: " + id).build();
		}
		return Response.ok(familiaBean.obtenerPorId(id)).build();
	}
	
	@GET
	@Path("/nombre/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFamiliasByNombre(@PathParam("nombre") String nombre) {
		try {
			familiaBean.obtenerTodosPorNombre(nombre).get(0);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existen familias con el nombre: " + nombre).build();
		}
		return Response.ok(familiaBean.obtenerTodosPorNombre(nombre)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFamilia(Familia familia){
		try {
		familiaBean.crear(familia);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFamilia(Familia familia){
		try {
			familiaBean.actualizar(familia);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFamilia(@PathParam("id") Long id){
		try {
			familiaBean.eliminar(id);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
}
