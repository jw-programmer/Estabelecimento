package br.com.jwprogrammer.estabelecimento;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.jwprogrammer.estabelecimento.repositories.EstabelecimentoRepository;
import br.com.jwprogrammer.estabelecimento.repositories.ProfissionalRepository;
import br.com.jwprogrammer.estabelecimento.services.EstabelecimentoService;
import br.com.jwprogrammer.estabelecimento.domain.Estabelecimento;
import br.com.jwprogrammer.estabelecimento.domain.Profissional;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class EstabelecimentoTests {

    @Autowired
    private EstabelecimentoService service;

    @Autowired
    private EstabelecimentoRepository repo;

    @Autowired
    ProfissionalRepository profissionalRepo;

    private Estabelecimento previo;

    @BeforeEach
    void onSetUp() {
        previo = new Estabelecimento(null, "Soares", "Edson Queiros", "8598656");
        previo = repo.save(previo);
    }

    @Test
    public void ServicoCriaUmEstabelecimento() throws Exception {
        Estabelecimento estabelecimento = new Estabelecimento(null, "Cormecio carajas", "Washiton Soares", "32986103");
        Estabelecimento novo = service.createEstabelecimento(estabelecimento);
        assertNotNull(novo);

        assertNotNull(novo.getId());
        assertTrue(novo.getId() instanceof Integer);

        Optional<Estabelecimento> banco = repo.findById(novo.getId());

        assertEquals(novo, banco.get());
    }

    @Test
    public void ServicoRetornaUmEstabelecimento() throws Exception {
        Estabelecimento obj = service.findEstabelecimento(previo.getId());
        assertNotNull(obj);
        assertEquals(previo, obj);
    }

    @Test
    public void ServicoDispareExeccaoDeNaoEncontrado() throws Exception {
        assertThrows(ObjectNotFoundException.class, () -> {
            service.findEstabelecimento(99999999);
        });
    }

    @Test
    public void ServicoEditaUmEstabelecimento() throws Exception {
        previo.setNome("Lojas Freitas");
        previo.setTelefone("111111");

        Estabelecimento editado = service.updateEstabelecimento(previo);

        assertEquals(previo, editado);
        assertEquals(previo.getNome(), editado.getNome());
        assertEquals(previo.getTelefone(), editado.getTelefone());
    }

    @Test
    public void ServicoAdicionaProfissionaisAoEstabelecimento() {
        Profissional p1 = new Profissional(null, "Wando", "Rua 2", "5541541515", "55555555", "vendas");
        Profissional p2 = new Profissional(null, "Nando", "Rua 5", "5541542215", "55566655", "estoque");
        profissionalRepo.saveAll(Arrays.asList(p1, p2));

        Optional<Profissional> atual1 = profissionalRepo.findById(p1.getId());
        Optional<Profissional> atual2 = profissionalRepo.findById(p2.getId());
        previo.getProfissionais().addAll(Arrays.asList(atual1.get(), atual2.get()));

        Estabelecimento atual = service.updateEstabelecimento(previo);

        assertEquals(previo, atual);
        assertArrayEquals(previo.getProfissionais().toArray(), atual.getProfissionais().toArray());

    }

    @Test
    public void ServicoDeletaEstabelecimento() throws Exception {
        service.deleteEstabelecimento(previo.getId());

        Optional<Estabelecimento> banco = repo.findById(previo.getId());

        assertTrue(banco.isEmpty());
    }

}
