package com.agenda.agenda_contacto.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.agenda.agenda_contacto.models.*;

@Repository
public interface ContactoRepository extends MongoRepository<Contacto, String> {
    Optional<Contacto> findByNumeroTelefono(String numeroTelefono);
}