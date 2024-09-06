package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import com.banana.bananawhatsapp.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
class ServicioMensajeriaTest {
    @Autowired
    IUsuarioRepository repoUsuario;
    @Autowired
    IServicioMensajeria servicio;

    @BeforeEach
    void cleanAndReloadData() {
        DBUtil.reloadDB();
    }

    @Test
    void dadoRemitenteYDestinatarioYTextoValido_cuandoEnviarMensaje_entoncesMensajeValido() throws Exception {
        Optional<Usuario> remitente = repoUsuario.findById(1);
        Optional<Usuario> destinatario = repoUsuario.findById(2);
        String texto = "Felices Fiestas!";
        Mensaje message = servicio.enviarMensaje(remitente.get(), destinatario.get(), texto);
        assertThat(message.getId(), greaterThan(0));
    }



    @Test
    void dadoRemitenteYDestinatarioYTextoNOValido_cuandoEnviarMensaje_entoncesExcepcion() throws Exception {
        Optional<Usuario> remitente = repoUsuario.findById(1);
        Optional <Usuario> destinatario = repoUsuario.findById(2);
        String texto = "SMS < 10";
        assertThrows(Exception.class, () -> {
            servicio.enviarMensaje(remitente.get(), destinatario.get(), texto);
        });
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoMostrarChatConUsuario_entoncesListaMensajes() throws Exception {
        Optional<Usuario> remitente = repoUsuario.findById(1);
        Optional<Usuario> destinatario = repoUsuario.findById(2);

        Optional<List<Mensaje>> userMessages = servicio.mostrarChatConUsuario(remitente.get(), destinatario.get());
        assertNotNull(userMessages);
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoMostrarChatConUsuario_entoncesExcepcion() throws Exception {
        Optional<Usuario> remitente = repoUsuario.findById(1);
        Usuario destinatario = new Usuario(2, null, null, null, false);
        assertThrows(Exception.class, () -> {
            Optional<List<Mensaje>> userMessages = servicio.mostrarChatConUsuario(remitente.get(), destinatario);
        });
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoBorrarChatConUsuario_entoncesOK() throws Exception {
        Optional<Usuario> remitente = repoUsuario.findById(1);
        Optional<Usuario> destinatario = repoUsuario.findById(2);
        boolean borrarChat = servicio.borrarChatConUsuario(remitente.get(), destinatario.get());
        assertTrue(borrarChat);
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoBorrarChatConUsuario_entoncesExcepcion() throws Exception {
        Optional<Usuario> remitente = repoUsuario.findById(1);
        Usuario destinatario = new Usuario(2, null, null, null, false);
        assertThrows(Exception.class, () -> {
            boolean borrarChat = servicio.borrarChatConUsuario(remitente.get(), destinatario);
        });
    }

}