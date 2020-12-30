package br.com.jwprogrammer.estabelecimento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.jwprogrammer.estabelecimento.domain.Profissional;
import br.com.jwprogrammer.estabelecimento.repositories.ProfissionalRepository;
import br.com.jwprogrammer.estabelecimento.services.ProfissionalService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProfissionalTests {
    
    @Autowired
    private ProfissionalService service;

    @Autowired ProfissionalRepository repo;

    private Profissional previo;

    @BeforeEach
    void setUp(){
        previo = new Profissional(null, "Josias", "Rua x - 124", "15141566", "6965666","T.I");
        previo = repo.save(previo);
    }

    @Test
    void ServicoPodeCriarProfissional(){
        Profissional profissional = new Profissional(null, "Wando", "Rua x - 125", "15141567", "6965646","vendas");
        Profissional novo = service.createProfissional(profissional);
        assertNotNull(novo);

        assertNotNull(novo.getId());
        assertTrue(novo.getId() instanceof Integer);

        Optional<Profissional> banco = repo.findById(novo.getId());

        assertEquals(novo, banco.get());
    }

    @Test
    public void ServicoRetornaUmProfissional() throws Exception {
        Profissional obj = service.findProfissional(previo.getId());
        assertNotNull(obj);
        assertEquals(previo, obj);
    }

    @Test
    public void ServicoPesquisaPorfissionalPorNome(){
        Profissional p2 = new Profissional(null, "Wando", "Rua x - 125", "15141567", "6965646","Estoque");
        Profissional p3 = new Profissional(null, "Vando", "Rua x - 125", "15141567", "6965646","Gerente");

        List<Profissional> atuais = repo.saveAll(Arrays.asList(p2, p3));
        
        List<Profissional> resultados = service.searchProfissionalByName("ando");

        assertTrue(resultados.contains(atuais.get(0)));
        assertTrue(resultados.contains(atuais.get(1)));
        assertFalse(resultados.contains(previo));
    }

    @Test
    public void ServicoDispareExeccaoDeNaoEncontrado() throws Exception {
        assertThrows(ObjectNotFoundException.class, () -> {
            service.findProfissional(66526526);
        });
    }


    @Test
    public void ServicoEditaUmProfissional() throws Exception {
        previo.setNome("Nobre");
        previo.setTelefoneCelular("2222222");
        previo.setTelefoneResidencial("615616511");
        previo.setFuncao("Empresário");

        Profissional editado = service.updateProfissional(previo);

        assertEquals(previo, editado);
        assertEquals(previo.getNome(), editado.getNome());
        assertEquals(previo.getTelefoneCelular(), editado.getTelefoneCelular());
        assertEquals(previo.getTelefoneResidencial(), editado.getTelefoneResidencial());
        assertEquals(previo.getFuncao(), editado.getFuncao());
    }

    @Test
    public void ServicoDeletaProfissional() throws Exception {
        service.deleteProfissional(previo.getId());

        Optional<Profissional> banco = repo.findById(previo.getId());

        assertTrue(banco.isEmpty());
    }
}
