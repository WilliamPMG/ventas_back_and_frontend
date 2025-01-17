package com.api.combodigital.repositories;

import com.api.combodigital.entities.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion,Long> {

}
