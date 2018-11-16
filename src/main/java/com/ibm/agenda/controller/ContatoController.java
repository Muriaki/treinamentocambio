package com.ibm.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.agenda.model.Contato;
import com.ibm.agenda.service.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private ContatoService contatoService;

    @Autowired
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaContato(@PathVariable Long id) {
        Contato contato = contatoService.buscarContato(id);

        return ResponseEntity.ok(new Resposta(0, "", contato));

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaTodosContato(@RequestParam(value = "filtro", required = false) String filtro) {
        List<Contato> contatos = contatoService.buscarTodosContato(filtro);
        return ResponseEntity.ok(new Resposta(0, "", contatos));
    }
    
    
    /**
     * 
     * @param contato
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> novoContato(@RequestBody Contato contato) {
        try {
            return ResponseEntity.ok(contatoService.salvarContato(contato));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateContato(@RequestBody Contato contato) {
        try {
            return ResponseEntity.ok(contatoService.atualizarContato(contato));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteContato(@PathVariable Long id) {
        try {
            contatoService.deletarContato(id);
            return ResponseEntity.ok("Contato removido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}

