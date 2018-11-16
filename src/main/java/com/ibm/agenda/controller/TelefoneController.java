package com.ibm.agenda.controller;

import com.ibm.agenda.model.Telefone;
import com.ibm.agenda.repository.ContatoRepository;
import com.ibm.agenda.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {
    private TelefoneRepository telefoneRepository;
    private ContatoRepository contatoRepository;

    @Autowired
    public TelefoneController(TelefoneRepository telefoneRepository,
                              ContatoRepository contatoRepository) {
        this.telefoneRepository = telefoneRepository;
        this.contatoRepository = contatoRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Telefone> buscaTelefone(@PathVariable Long id) {
        Optional<Telefone> telefoneOptional = telefoneRepository.findById(id);
        return telefoneOptional.isPresent() ? ResponseEntity.ok(telefoneOptional.get()) :
                ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Telefone> novoTelefone(@RequestBody Telefone telefone) {
        if (telefone.getContato() == null || telefone.getContato().getId() == null)
            return ResponseEntity.badRequest().build();
        if (!contatoRepository.existsById(telefone.getContato().getId()))
            return ResponseEntity.notFound().build();
        Telefone savedTelefone = telefoneRepository.save(telefone);
        return ResponseEntity.ok(savedTelefone);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Telefone> updateTelefone(@RequestBody Telefone telefone) {
        if (telefone == null || telefone.getId() == null)
            return ResponseEntity.badRequest().build();
        if (!telefoneRepository.existsById(telefone.getId()))
            return ResponseEntity.notFound().build();

        Telefone telefoneUpdated = telefoneRepository.save(telefone);
        return ResponseEntity.ok(telefoneUpdated);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTelefone(@PathVariable Long id) {
        if (id == null)
            return ResponseEntity.badRequest().build();
        if (!telefoneRepository.existsById(id))
            return ResponseEntity.notFound().build();

        telefoneRepository.deleteById(id);
        return ResponseEntity.ok("Telefone removido com sucesso!");
    }
}

