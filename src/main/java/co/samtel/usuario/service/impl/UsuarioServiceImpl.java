package co.samtel.usuario.service.impl;

import co.samtel.usuario.constant.Constant;
import co.samtel.usuario.controller.UsuarioController;
import co.samtel.usuario.dao.UsuarioDao;
import co.samtel.usuario.entity.Usuario;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import co.samtel.usuario.service.contract.IUsuarioService;
import co.samtel.usuario.utils.ApplicationException;
import co.samtel.usuario.utils.UsuarioMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static co.samtel.usuario.constant.Constant.ERROR_SERVICIO;

@ApplicationScoped
public class UsuarioServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    //Inyección de dependencias
    @Inject
    UsuarioMapper usuarioMapper;
    /*  @Inject
    IUsuarioService iUsuarioService;
    */
    @Inject
    UsuarioDao usuarioDao;

    @Transactional
    public UsuarioTypeResponse crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicia crearUsuario Impl");
        UsuarioTypeResponse usuarioTypeResponses;
        try {
            Usuario usuario = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
            usuarioDao.persist(usuario);
            usuarioTypeResponses = usuarioMapper.usuarioEntityToTypeResponse(usuario);
            LOG.info("Finaliza crear usuario Impl");
        } catch (ApplicationException e) {
            LOG.error("Error al crear usuario Impl" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());

        }
        return usuarioTypeResponses;
    }

    public List<UsuarioTypeResponse> listarUsuario(Integer idtblUser){
        LOG.info("Inicia CrearUsuarioImpl");
        try {
            long id = Long.valueOf(idtblUser);
            Usuario user = usuarioDao.findById(id);
            UsuarioTypeResponse response = usuarioMapper.usuarioEntityToTypeResponse(user);
            LOG.info("Finaliza listar usuario por id");
            return Collections.singletonList(response);
        } catch (ApplicationException e){
            LOG.error("Se presento un error al listar usuario por id" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }

    }

    @Transactional
    public void eliminarUsuario(Integer idtblUser){
        LOG.info("Inicia eliminar usuario Impl");
        try {
            long id = Long.valueOf(idtblUser);
            usuarioDao.deleteById(id);
            LOG.info("Se finaliza eliminacion de usuario por id Impl");
        } catch (ApplicationException e){
            LOG.error(ERROR_SERVICIO +e.getMessage() + "Eliminar usuario Impl");
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }

    }

    @Transactional
    public UsuarioTypeResponse actualizaUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicia el metodo editarUsuario Impl");
        try{
            Usuario usuario = usuarioDao.findById(idtblUser.longValue());
            Usuario usuarioCambio = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
            usuario.setName(usuarioCambio.getName());
            usuario.setLastname(usuarioCambio.getLastname());
            UsuarioTypeResponse response = usuarioMapper.usuarioTypeToTypeResponse(usuarioCambio);
            LOG.info("Finaliza el metodo editarUsuario Impl");
            return response;

        }catch(ApplicationException e){
            LOG.error("Se presento un error en el metodo editarUsuario Impl"+ e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }
}

