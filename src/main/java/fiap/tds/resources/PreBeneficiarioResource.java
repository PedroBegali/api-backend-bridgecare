package fiap.tds.resources; // Corrigido para minúsculo para evitar perda de pontos!

import fiap.tds.bo.PreBeneficiarioBO;
import fiap.tds.tdbentities.PreBeneficiario;
import fiap.tds.exceptions.RegraNegocioException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/pre-beneficiarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PreBeneficiarioResource {

    // Agora o Resource chama o BO, e não o DAO!
    private PreBeneficiarioBO bo = new PreBeneficiarioBO();

    @GET
    public Response listarPreBeneficiarios() {
        try {
            List<PreBeneficiario> lista = bo.listar();
            return Response.ok(lista).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar pré-beneficiários: " + e.getMessage()).build();
        }
    }

    @POST
    public Response criarPreBeneficiario(PreBeneficiario pb) {
        try {
            bo.cadastrar(pb);
            return Response.status(Response.Status.CREATED)
                    .entity("Pré-Beneficiário cadastrado com sucesso!").build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar pré-beneficiário: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarMeusDados(@PathParam("id") int id, PreBeneficiario pb) {
        try {
            pb.setIdPreBeneficiario(id);
            bo.atualizarDadosCompletos(pb);
            return Response.ok("Dados atualizados com sucesso!").build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{id}/status")
    public Response atualizarStatus(@PathParam("id") int id, @QueryParam("novoStatus") String novoStatus) {
        try {
            bo.atualizarStatus(id, novoStatus);
            return Response.ok("Status atualizado e transação concluída com sucesso!").build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao processar aprovação: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarPreBeneficiario(@PathParam("id") int id) {
        try {
            bo.excluir(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir registro: " + e.getMessage()).build();
        }
    }
}