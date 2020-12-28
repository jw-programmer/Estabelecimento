package br.com.jwprogrammer.estabelecimento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jwprogrammer.estabelecimento.repositories.EstabelecimentoRepository;
import br.com.jwprogrammer.estabelecimento.services.EstabelecimentoService;
import br.com.jwprogrammer.estabelecimento.domain.Estabelecimento;

@SpringBootTest
public class EstabelecimentoTests{

    @Autowired
    private EstabelecimentoService service;

    @Autowired
    private EstabelecimentoRepository repo;

    private Estabelecimento previo;

    @BeforeEach
    void onSetUp() {
        System.out.println("Isso é Chamado mais vez");
        previo = new Estabelecimento(1, "Soares", "Edson Queiros", "8598656", null);
        repo.save(previo);
    }

    @Test
    public void ServicoCriaUmEstabelecimento() throws Exception {
        Estabelecimento estabelecimento = new Estabelecimento(null, "Cormecio carajas", "Washiton Soares", "32986103",
                null);
        Estabelecimento novo = service.createEstabelecimento(estabelecimento);
        assertNotNull(novo);

        assertNotNull(novo.getId());
        assertTrue(novo.getId() instanceof Integer);

        Optional<Estabelecimento> banco = repo.findById(novo.getId());

        assertEquals(novo, banco.get());
    }

    @Test
    public void ServicoRetornaUmEstabelecimento() throws Exception {
        Estabelecimento obj = service.findEstabelecimento(1);
        assertNotNull(obj);
        assertEquals(previo, obj);
    }

    @Test
    public void ServicoDispareExeccaoDeNaoEncontrado() throws Exception {
        assertThrows(ObjectNotFoundException.class, () -> {
            service.findEstabelecimento(2);
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
    public void ServicoDeletaEstabelecimento() throws Exception {
        service.deleteEstabelecimento(previo.getId());

        Optional<Estabelecimento> banco = repo.findById(previo.getId());

        assertTrue(banco.isEmpty());
    }

    @AfterEach
    void onTearDown() {
        repo.delete(previo);
        previo = null;
    }

}
