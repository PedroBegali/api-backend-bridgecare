package fiap.tds.resources;

import fiap.tds.bo.EnderecoBO;
import fiap.tds.tdbentities.Endereco;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    private EnderecoBO bo = new EnderecoBO();

    @POST
    public Response cadastrarEndereco(Endereco endereco) {
        try {
            bo.cadastrar(endereco);
            return Response.status(Response.Status.CREATED)
                    .entity("Endereço de triagem cadastrado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar endereço no banco: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarEnderecos() {
        try {
            return Response.ok(bo.listar()).build();
        } catch (SQLException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}