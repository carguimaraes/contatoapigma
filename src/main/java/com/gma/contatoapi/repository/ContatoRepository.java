package com.gma.contatoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gma.contatoapi.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
