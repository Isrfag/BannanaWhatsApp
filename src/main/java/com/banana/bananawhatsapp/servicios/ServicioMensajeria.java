package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioMensajeria implements  IServicioMensajeria{

    @Autowired
    private IMensajeRepository menRepo;
    @Override
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException {
        if(!remitente.valido()) {
            throw new UsuarioException();
        }
        else if(!destinatario.valido()) {
            throw new UsuarioException();
        }
        else {
            Mensaje m = new Mensaje(null, remitente,destinatario,texto, LocalDate.now());
            if(m.valido()) {
                menRepo.save(m);
                return m;
            }else {
                throw new MensajeException();
            }
        }


    }

    @Override
    public Optional<List<Mensaje>> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
         if(!remitente.valido()) {
            throw new UsuarioException();
        }
        else if(!destinatario.valido()) {
            throw new UsuarioException();
        }
        else {
            Optional<List<Mensaje>> chat = Optional.of(menRepo.findAll());
            if(!chat.isPresent()) {
                return chat;
            }else {
                throw new MensajeException();
            }
        }
    }

    @Override
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        return false;
    }
}
