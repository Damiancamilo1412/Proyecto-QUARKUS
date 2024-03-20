package co.samtel.usuario.utils;

import co.samtel.usuario.entity.Usuario;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class UsuarioMapper {

    public Usuario usuarioTypeToEntity(UsuarioTypeInput usuarioType){
        return new ModelMapper().map(usuarioType, Usuario.class);

    }

    public UsuarioTypeInput usuarioEntityToType(Usuario usuario){
        return new ModelMapper().map(usuario, UsuarioTypeInput.class);
    }
    public UsuarioTypeResponse usuarioEntityToTypeResponse(Usuario usuario){
        return new ModelMapper().map(usuario, UsuarioTypeResponse.class);
    }

    public UsuarioTypeResponse usuarioTypeToTypeResponse(Usuario usuario) {
        return new ModelMapper().map(usuario, UsuarioTypeResponse.class);


    }
}