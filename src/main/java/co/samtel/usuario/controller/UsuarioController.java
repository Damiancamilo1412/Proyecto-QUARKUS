package co.samtel.usuario.controller;

import co.samtel.usuario.constant.Constant;
import co.samtel.usuario.entity.Usuario;
import co.samtel.usuario.gen.contract.V1UsuarioApi;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import co.samtel.usuario.service.impl.UsuarioServiceImpl;
import co.samtel.usuario.utils.ApplicationException;
import co.samtel.usuario.utils.UsuarioMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class UsuarioController implements V1UsuarioApi {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);

    @Inject
    UsuarioServiceImpl usuarioServiceImpl;

    @Inject
    UsuarioMapper usuarioMapper;

    @Override
    public Response crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Se inicia el proceso crear usuario controller");
        UsuarioTypeResponse usuarioType = null;
        try {
            usuarioType = usuarioServiceImpl.crearUsuario(usuarioTypeInput);
        } catch (ApplicationException e) {
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "CrearUsuarioController");
            return Response.status(Response.Status.BAD_REQUEST).entity(usuarioType).build();
        }
        LOG.info("Finaliza crear usuario controller");
        return Response.status(Response.Status.CREATED).entity(usuarioType).build();
    }

    @Override
    public Response eliminarUsuario(Integer idtblUser) {
        LOG.info("Se inicia la eliminacion del usuario por Id Controller");
        try {
            usuarioServiceImpl.eliminarUsuario(idtblUser);
        } catch (ApplicationException e) {
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "eliminaUsuarioController");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        LOG.info("Finaliza eliminacion usuario controller");
        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @Override
    public Response actualizaUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Se Inicia el proceso de actualizacion controller");
        UsuarioTypeResponse usuarioType = null;
        try {
            usuarioType = usuarioServiceImpl.actualizaUsuario(idtblUser, usuarioTypeInput);
        } catch (ApplicationException e) {
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "ActualizarUsuarioController");
            return Response.status(Response.Status.BAD_REQUEST).entity(usuarioType).build();
        }
        LOG.info("Finaliza actualizacion usuario controller");
        return Response.status(Response.Status.OK).entity(usuarioType).build();
    }

    @Override
    public Response buscarUsuarioPorId(Integer idtblUser) {
        LOG.info("Se inicia buscar usuario por Id");
        List<UsuarioTypeResponse> usuarioType = null;
        try {
            usuarioType = usuarioServiceImpl.listarUsuario(idtblUser);
        } catch (ApplicationException e) {
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "buscarUsuarioController");
            return Response.status(Response.Status.BAD_REQUEST).entity(usuarioType).build();
        }
        LOG.info("Finaliza buscar usuario controller");
        return Response.status(Response.Status.CREATED).entity(usuarioType).build();
    }


}