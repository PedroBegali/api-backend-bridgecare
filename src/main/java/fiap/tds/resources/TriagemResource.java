package fiap.tds.resources;

import fiap.tds.bo.TriagemBO;
import fiap.tds.tdbentities.Triagem;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/triagens")
@Produces(MediaType.APPLICATION_JSON)
public class TriagemResource {

    private TriagemBO bo = new TriagemBO();

    @GET
    @Path("/proximas")
    public Response listarProximas() {
        try {
            return Response.ok(bo.listarProximas()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar triagens: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarTriagem(Triagem triagem) {
        try {
            bo.cadastrar(triagem); // ou dao.cadastrar(triagem) se não estiver a usar BO para as triagens
            return Response.status(Response.Status.CREATED).entity("Triagem agendada com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}