package fiap.tds.resources;

import fiap.tds.bo.DentistaBO;
import fiap.tds.exceptions.RegraNegocioException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/dentistas")
@Produces(MediaType.APPLICATION_JSON)
public class DentistaResource {

    private DentistaBO bo = new DentistaBO();

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            return Response.ok(bo.buscarPorId(id)).build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{id}/status")
    public Response mudarStatus(@PathParam("id") int id, @QueryParam("status") String status) {
        try {
            bo.mudarStatus(id, status);
            return Response.ok("Status do dentista atualizado com sucesso!").build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response listarTodos() {
        try {
            return Response.ok(bo.listar()).build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}