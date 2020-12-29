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

import br.com.jwprogrammer.estabelecimento.domain.Estabelecimento;
import br.com.jwprogrammer.estabelecimento.services.EstabelecimentoService;

@RestController
@RequestMapping(value = "/estabelecimentos")
public class EstabelecimentoResouce {

    @Autowired
    private EstabelecimentoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Estabelecimento>> findAll() {
        List<Estabelecimento> obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estabelecimento> findById( @PathVariable Integer id) {
        Estabelecimento obj = service.findEstabelecimento(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> CreateEstabelecimento( @RequestBody Estabelecimento estabelecimento) {
        Estabelecimento obj = service.createEstabelecimento(estabelecimento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Estabelecimento> updateById( @RequestBody  Estabelecimento obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = service.updateEstabelecimento(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Estabelecimento> deleteById(@PathVariable Integer id) {
        service.deleteEstabelecimento(1);
        return ResponseEntity.noContent().build();
    }
}
