package fiap.tds.resources;

import fiap.tds.bo.ConsultaBO;
import fiap.tds.exceptions.RegraNegocioException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
public class ConsultaResource {


    private ConsultaBO bo = new ConsultaBO();

    @GET
    public Response listarTodas() {
        try { return Response.ok(bo.listarTodas()).build(); }
        catch (SQLException e) { return Response.status(500).entity(e.getMessage()).build(); }
    }

    @GET
    @Path("/dentista/{id}/proximas")
    public Response proximasDentista(@PathParam("id") int idDentista) {
        try { return Response.ok(bo.listarProximasDentista(idDentista)).build(); }
        catch (SQLException e) { return Response.status(500).entity(e.getMessage()).build(); }
    }

    @GET
    @Path("/dentista/{id}/pendentes")
    public Response pendentesDentista(@PathParam("id") int idDentista) {
        try { return Response.ok(bo.listarPendentesDentista(idDentista)).build(); }
        catch (SQLException e) { return Response.status(500).entity(e.getMessage()).build(); }
    }

    @PATCH
    @Path("/{id}/prontuario")
    public Response preencherProntuario(@PathParam("id") int idConsulta, @QueryParam("texto") String texto) {
        try {
            bo.preencherProntuario(idConsulta, texto);
            return Response.ok("Prontuário salvo com sucesso!").build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/beneficiario/{id}/proximas")
    public Response proximasBeneficiario(@PathParam("id") int idBeneficiario) {
        try { return Response.ok(bo.listarProximasBeneficiario(idBeneficiario)).build(); }
        catch (SQLException e) { return Response.status(500).entity(e.getMessage()).build(); }
    }

    @GET
    @Path("/beneficiario/{id}/historico")
    public Response historicoBeneficiario(@PathParam("id") int idBeneficiario) {
        try { return Response.ok(bo.listarHistoricoBeneficiario(idBeneficiario)).build(); }
        catch (SQLException e) { return Response.status(500).entity(e.getMessage()).build(); }
    }
}