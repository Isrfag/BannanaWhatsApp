package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio implements IServicioUsuarios {

    @Autowired
    IUsuarioRepository usuarioRepo;

    @Override
    public Usuario obtener(int id) throws UsuarioException {
            return usuarioRepo.findById(id).orElseThrow(()->new UsuarioException("Error en la obtenciónError en la obtención"));
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {

        try {
            usuario.valido();
            usuarioRepo.save(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsuarioException("Error en la creación");
        }

        return usuario;
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {
        try {
            usuarioRepo.deleteById(usuario.getId());
            return usuarioRepo.existsById(usuario.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsuarioException("Error en el borrado");
        }


    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        try {
            usuario.valido();
            return usuarioRepo.save(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsuarioException("Error en la actualización");
        }
    }

    @Override
    public List<Usuario> obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        try {
            usuario.valido();
            return usuarioRepo.findByIdAndDestinatario(usuario.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsuarioException("Error en obtener destinatarios");
        }
    }
}
