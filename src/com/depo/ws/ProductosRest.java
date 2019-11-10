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

import com.entities.Producto;
import com.exception.ServicesException;
import com.services.ProductoBeanRemote;

@Path("/productos")
public class ProductosRest {
	
	@EJB
	private ProductoBeanRemote productoBean;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductos(){
		return Response.ok(productoBean.obtenerTodos()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductoById(@PathParam("id") Long id) {
		try {
			productoBean.obtenerPorId(id);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe un producto con el id: " + id).build();
		}
		return Response.ok(productoBean.obtenerPorId(id)).build();
	}
	
	@GET
	@Path("/nombre/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductosByNombre(@PathParam("nombre") String nombre) {
		try {
			productoBean.obtenerTodosPorNombre(nombre).get(0);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existen productos con el nombre: " + nombre).build();
		}
		return Response.ok(productoBean.obtenerTodosPorNombre(nombre)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProducto(Producto producto){
		try {
		productoBean.crear(producto);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProducto(Producto producto){
		try {
			productoBean.actualizar(producto);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProducto(@PathParam("id") Long id){
		try {
			productoBean.eliminar(id);
		} catch (ServicesException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}
}
