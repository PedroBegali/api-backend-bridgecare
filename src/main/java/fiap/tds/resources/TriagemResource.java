package fiap.tds.resources;

import fiap.tds.bo.TriagemBO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
}