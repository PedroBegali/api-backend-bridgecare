package fiap.tds.resources;

import fiap.tds.bo.RelTriagemPreBenefBO;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/rel-triagem")
@Produces(MediaType.APPLICATION_JSON)
public class RelTriagemPreBenefResource {

    private RelTriagemPreBenefBO bo = new RelTriagemPreBenefBO();

    @PATCH
    @Path("/pre-beneficiario/{id}/reagendar")
    public Response reagendar(@PathParam("id") int idPreBeneficiario, @QueryParam("idNovaTriagem") int idNovaTriagem) {
        try {
            bo.atualizarTriagem(idPreBeneficiario, idNovaTriagem);
            return Response.ok("Reagendamento feito com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao reagendar triagem: " + e.getMessage()).build();
        }
    }
}