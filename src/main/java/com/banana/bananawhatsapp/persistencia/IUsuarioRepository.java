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
import java.util.Set;


public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u INNER JOIN Mensaje m ON m.destinatario = u.id WHERE m.remitente = :user")
    public List<Usuario> findByIdAndDestinatario(@Param("user")int Iduser);

    public default void findById (Usuario usuario) throws UsuarioException {
        if (usuario.getId() < 1 ) throw new UsuarioException("usuario inválido");
    }

    //public Usuario obtener(int id) throws SQLException;
    //public Usuario crear(Usuario usuario) throws SQLException;

    //public Usuario actualizar(Usuario usuario) throws SQLException;

    //public boolean borrar(Usuario usuario) throws SQLException;

    //public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException;

}
