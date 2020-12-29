package br.com.jwprogrammer.estabelecimento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jwprogrammer.estabelecimento.domain.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
    
    List<Profissional> findByNomeContaining(String nome);
}
