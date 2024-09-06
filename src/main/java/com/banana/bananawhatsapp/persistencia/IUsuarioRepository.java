package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u INNER JOIN Mensaje m ON m.destinatario = u.id WHERE u.id = :id")
    public List<Usuario> findByIdAndDestinatario(@Param("id")Integer id) throws UsuarioException;



    public default Optional saveValidado (Usuario usuario) throws UsuarioException {
        if (usuario.getId() < 1 ) {throw new UsuarioException("usuario inválido");} else {
            return Optional.of( save(usuario));
        }
    }

    public default void deleteValidado (Usuario usuario) throws UsuarioException {
        if (usuario.getId() < 1 ) {throw new UsuarioException("usuario inválido");} else {
            delete(usuario);
        }
    }

    public default List<Usuario> findByIdAndDestinatarioValidado (int id) throws UsuarioException {
        if (id < 1 ) {throw new UsuarioException("usuario inválido");} else {
            return findByIdAndDestinatario(id);
        }
    }

}
