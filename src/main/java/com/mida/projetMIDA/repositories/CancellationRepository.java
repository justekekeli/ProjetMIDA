package com.mida.projetMIDA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mida.projetMIDA.models.Cancellation;

public interface CancellationRepository extends JpaRepository<Cancellation,Long> {

}
