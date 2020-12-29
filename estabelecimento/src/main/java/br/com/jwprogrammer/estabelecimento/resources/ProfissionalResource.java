package br.com.jwprogrammer.estabelecimento.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jwprogrammer.estabelecimento.domain.Profissional;
import br.com.jwprogrammer.estabelecimento.services.ProfissionalService;

@RestController
@RequestMapping(value = "profissionais")
public class ProfissionalResource {
    @Autowired
    private ProfissionalService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Profissional>> findAll() {
        List<Profissional> obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Profissional> findById(@PathVariable Integer id) {
        Profissional obj = service.findProfissional(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> CreateEstabelecimento(@RequestBody Profissional profissional) {
        Profissional obj = service.createProfissional(profissional);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Profissional> updateById(@RequestBody Profissional obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = service.updateProfissional(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Profissional> deleteById(@PathVariable Integer id) {
        service.deleteProfissional(1);
        return ResponseEntity.noContent().build();
    }
}
