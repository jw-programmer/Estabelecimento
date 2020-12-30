package br.com.jwprogrammer.estabelecimento.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jwprogrammer.estabelecimento.domain.Estabelecimento;
import br.com.jwprogrammer.estabelecimento.domain.Profissional;
import br.com.jwprogrammer.estabelecimento.repositories.EstabelecimentoRepository;
import br.com.jwprogrammer.estabelecimento.repositories.ProfissionalRepository;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repo;

    @Autowired
    private ProfissionalRepository profissionalRepo;

    @Transactional
    public Estabelecimento createEstabelecimento(Estabelecimento novoEstabelecimento) {
        novoEstabelecimento.setId(null);
        novoEstabelecimento = repo.save(novoEstabelecimento);
        return novoEstabelecimento;
    }

    public List<Estabelecimento> findAll() {
        return repo.findAll();
    }

    public Estabelecimento findEstabelecimento(Integer id) throws ObjectNotFoundException {
        Optional<Estabelecimento> estabelecimento = repo.findById(id);
        return estabelecimento
                .orElseThrow(() -> new ObjectNotFoundException(1L, "Não existe estabelecimento com esse id"));
    }

    public Estabelecimento updateEstabelecimento(Estabelecimento estabelecimento) throws ObjectNotFoundException {
        Estabelecimento persisted = findEstabelecimento(estabelecimento.getId());
        updateData(persisted, estabelecimento);
        return repo.save(persisted);
    }

    @Transactional
    private void updateData(Estabelecimento novo, Estabelecimento atual) {
        novo.setNome(atual.getNome());
        novo.setTelefone(atual.getTelefone());
        novo.setEndereco(atual.getEndereco());
        repo.save(novo);
        novo.getProfissionais().addAll(atual.getProfissionais());
        for (Profissional p : novo.getProfissionais()) {
            p.getEstabelecimentos().add(novo);
        }
        profissionalRepo.saveAll(novo.getProfissionais());
    }

    public void deleteEstabelecimento(Integer id) throws ObjectNotFoundException {
        findEstabelecimento(id);
        repo.deleteById(id);
    }
}
