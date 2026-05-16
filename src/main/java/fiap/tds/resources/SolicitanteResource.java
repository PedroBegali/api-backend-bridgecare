package fiap.tds.resources;

import fiap.tds.bo.SolicitanteBO;
import fiap.tds.tdbentities.Solicitante;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/solicitantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitanteResource {

    private SolicitanteBO bo = new SolicitanteBO();

    @GET
    public Response listarSolicitantes() {
        try {
            return Response.ok(bo.listar()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar solicitantes: " + e.getMessage()).build();
        }
    }

    @POST
    public Response criarSolicitante(Solicitante solicitante) {
        try {
            bo.cadastrar(solicitante);
            return Response.status(Response.Status.CREATED).entity("Solicitante cadastrado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar solicitante: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarSolicitante(@PathParam("id") int id) {
        try {
            bo.excluir(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir solicitante: " + e.getMessage()).build();
        }
    }
}