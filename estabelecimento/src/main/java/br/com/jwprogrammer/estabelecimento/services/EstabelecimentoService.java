package br.com.jwprogrammer.estabelecimento.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jwprogrammer.estabelecimento.domain.Estabelecimento;
import br.com.jwprogrammer.estabelecimento.repositories.EstabelecimentoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repo;

    @Transactional
    public Estabelecimento createEstabelecimento(Estabelecimento novoEstabelecimento) {
        novoEstabelecimento.setId(null);
        novoEstabelecimento = repo.save(novoEstabelecimento);
        return novoEstabelecimento;
    }

    public Estabelecimento findEstabelecimento(Integer id) throws ObjectNotFoundException {
        Optional<Estabelecimento> estabelecimento = repo.findById(id);
        return estabelecimento.orElseThrow(() -> new ObjectNotFoundException("Não existe estabelecimento com esse id"));
    }

    public Estabelecimento updateEstabelecimento(Estabelecimento estabelecimento) throws ObjectNotFoundException {
        Estabelecimento persisted = findEstabelecimento(estabelecimento.getId());
        updateData(persisted, estabelecimento);
        return persisted;
    }

    private void updateData(Estabelecimento novo, Estabelecimento atual) {
        novo.setNome(atual.getNome());
        novo.setTelefone(atual.getTelefone());
        novo.setEndereco(atual.getEndereco());
    }

    public void deleteEstabelecimento(Integer id) throws ObjectNotFoundException {
        findEstabelecimento(id);
        repo.deleteById(id);
    }
}
