package br.com.jwprogrammer.estabelecimento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jwprogrammer.estabelecimento.domain.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {
    
}
