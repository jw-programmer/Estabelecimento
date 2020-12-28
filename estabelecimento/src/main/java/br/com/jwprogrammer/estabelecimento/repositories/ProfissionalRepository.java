package br.com.jwprogrammer.estabelecimento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jwprogrammer.estabelecimento.domain.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
    
}
