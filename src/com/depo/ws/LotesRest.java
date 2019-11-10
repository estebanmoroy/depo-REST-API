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

import com.entities.Lote;
import com.exception.ServicesException;
import com.services.LoteBeanRemote;

@Path("/lotes")
public class LotesRest {
	
	@EJB
	private LoteBeanRemote loteBean;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLotes(){
		return Response.ok(loteBean.obtenerTodos()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLoteById(@PathParam("id") Long id) {
		try {
			loteBean.obtenerPorId(id);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe un lote con el id: " + id).build();
		}
		return Response.ok(loteBean.obtenerPorId(id)).build();
	}
	
	@GET
	@Path("/codigo/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLotesByCodigo(@PathParam("codigo") String codigo) {
		try {
			loteBean.obtenerTodosPorCodigo(codigo).get(0);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existen lotes con el codigo: " + codigo).build();
		}
		return Response.ok(loteBean.obtenerTodosPorCodigo(codigo)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLote(Lote lote){
		try {
		loteBean.crear(lote);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateLote(Lote lote){
		try {
			loteBean.actualizar(lote);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLote(@PathParam("id") Long id){
		try {
			loteBean.eliminar(id);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
}