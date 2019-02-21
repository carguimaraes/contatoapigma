package com.gma.contatoapi.model.repositorio;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gma.contatoapi.model.entidade.Contato;

/*
 * 2019-02-21
 * GMA - Carlos A L M Guimaraes
 * 
 */


//extends PaginationAndSortingRepository
// extends JpaRepository
public interface ContatoRepository extends PagingAndSortingRepository<Contato, Long> {

}
