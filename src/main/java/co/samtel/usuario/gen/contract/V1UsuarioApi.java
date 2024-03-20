package co.samtel.usuario.gen.contract;

import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;




import java.io.InputStream;
import java.util.Map;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;


@Path("/v1/es")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2024-03-20T10:15:17.254538200-05:00[America/Bogota]")
public interface V1UsuarioApi {

    @PUT
    @Path("/actualizaUsuario/{idtbl_user}")
    @Consumes({ "application/json" })
    Response actualizaUsuario(@PathParam("idtbl_user") @Min(1) Integer idtblUser,@Valid UsuarioTypeInput usuarioTypeInput);

    @GET
    @Path("/buscarUsuarioPorId/{idtbl_user}")
    @Produces({ "application/json" })
    Response buscarUsuarioPorId(@PathParam("idtbl_user") @Min(1) Integer idtblUser);

    @POST
    @Path("/crearUsuario")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response crearUsuario(@Valid UsuarioTypeInput usuarioTypeInput);

    @DELETE
    @Path("/eliminarUsuario/{idtbl_user}")
    Response eliminarUsuario(@PathParam("idtbl_user") @Min(1) Integer idtblUser);
}
