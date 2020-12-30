package br.com.jwprogrammer.estabelecimento.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jwprogrammer.estabelecimento.domain.Estabelecimento;
import br.com.jwprogrammer.estabelecimento.domain.Profissional;
import br.com.jwprogrammer.estabelecimento.repositories.EstabelecimentoRepository;
import br.com.jwprogrammer.estabelecimento.repositories.ProfissionalRepository;

@Service
public class DbService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepo;

    @Autowired
    private ProfissionalRepository  pRepository;
    public void instanceDB(){
        Estabelecimento e1 = new Estabelecimento(null, "Casas Freitas", "Rua 2", "56565266");
        Estabelecimento e2 = new Estabelecimento(null, "Americanas", "Rua 2", "56565266");
        Estabelecimento e3 = new Estabelecimento(null, "Casas Freitas", "Rua 2", "56565266");

        estabelecimentoRepo.saveAll(Arrays.asList(e1, e2, e3));

        Profissional p1 = new Profissional(null, "Wando", "Rua 12", "1985632", "662626326", "T.I");
        Profissional p2 = new Profissional(null, "Nobre", "Rua 13", "246846732", "678890226", "Gerente");
        Profissional p3 = new Profissional(null, "Vando", "Rua 14", "111111111", "222222222", "Reposição");

        pRepository.saveAll(Arrays.asList(p1,p2,p3));
    }
}
