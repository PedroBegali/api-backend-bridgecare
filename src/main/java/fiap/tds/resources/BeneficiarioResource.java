package fiap.tds.resources;

import fiap.tds.bo.BeneficiarioBO;
import fiap.tds.tdbentities.Beneficiario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/beneficiarios")
@Produces(MediaType.APPLICATION_JSON)
public class BeneficiarioResource {

    // Agora chama o BO!
    private BeneficiarioBO bo = new BeneficiarioBO();

    @GET
    public Response listar() {
        try {
            return Response.ok(bo.listar()).build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Beneficiario beneficiario) {
        try {
            beneficiario.setIdBeneficiario(id);
            bo.atualizar(beneficiario);
            return Response.ok("Beneficiário atualizado!").build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") int id) {
        try {
            bo.excluir(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}