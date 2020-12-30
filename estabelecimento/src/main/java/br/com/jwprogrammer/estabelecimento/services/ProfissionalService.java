package br.com.jwprogrammer.estabelecimento.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jwprogrammer.estabelecimento.domain.Profissional;
import br.com.jwprogrammer.estabelecimento.repositories.ProfissionalRepository;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository repo;

    @Transactional
    public Profissional createProfissional(Profissional novoProfissional) {
        novoProfissional.setId(null);
        novoProfissional = repo.save(novoProfissional);
        return novoProfissional;
    }

    public List<Profissional> findAll() {
        return repo.findAll();
    }

    @Transactional
	public Profissional findProfissional(int id) throws ObjectNotFoundException {
		Optional<Profissional> profissional = repo.findById(id);
        return profissional.orElseThrow(() -> new ObjectNotFoundException(1L, "Não existe profissional com esse id"));
    }

    public List<Profissional> searchProfissionalByName(String nome) {
		return repo.findByNomeContaining(nome);
	}

	public Profissional updateProfissional(Profissional profissional) {
		Profissional persisted = findProfissional(profissional.getId());
        updateData(persisted, profissional);
        return repo.save(persisted);
    }
    
    private void updateData(Profissional profissionalNovo, Profissional profissional){
        profissionalNovo.setNome(profissional.getNome());
        profissionalNovo.setEndereco(profissional.getEndereco());
        profissionalNovo.setTelefoneResidencial(profissional.getTelefoneResidencial());
        profissionalNovo.setTelefoneCelular(profissional.getTelefoneCelular());
        profissionalNovo.setFuncao(profissional.getFuncao());
    }

	public void deleteProfissional(Integer id) {
        findProfissional(id);
        repo.deleteById(id);
	}
    
}
