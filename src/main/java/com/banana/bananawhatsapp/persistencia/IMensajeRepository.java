package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IMensajeRepository extends JpaRepository<Mensaje,Integer> {

    @Query("Select m FROM Mensaje m Where m.remitente!= null")
    List<Mensaje> findMensajeListByUsuario(Optional<Usuario> usuario);

     /*@Query ("DELETE m FROM Mensaje m INNER JOIN Usuario u ON u=:user AND u=:user2")
    public boolean deleteDouble (@Param("user") Optional<Usuario> user, @Param("user2") Optional<Usuario> user2);*/

    public default List<Mensaje> findMessageByUserException (Optional<Usuario> usuario) throws SQLException {
         if(usuario.get().getNombre()!= null) {
             List <Mensaje> list = findMensajeListByUsuario(usuario);
             return list;
        }else if (usuario.get().getEmail()!= null){
            List <Mensaje> list = findMensajeListByUsuario(usuario);
             return list;
        }else {
             throw new UsuarioException();
        }

    }

    public default void saveWithException (Mensaje mensaje) throws SQLException {
        if(mensaje.getRemitente().getNombre()!= null) {
            save(mensaje);
        }else if (mensaje.getDestinatario().getNombre()!= null){
            save(mensaje);
        }else {
            throw new MensajeException();
        }
    }


}


