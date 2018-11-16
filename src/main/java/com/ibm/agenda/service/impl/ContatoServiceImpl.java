package com.ibm.agenda.service.impl;

import com.ibm.agenda.exception.ObjetoNaoEcontratoException;
import com.ibm.agenda.model.Contato;
import com.ibm.agenda.model.Telefone;
import com.ibm.agenda.repository.ContatoRepository;
import com.ibm.agenda.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoServiceImpl implements ContatoService {
    private ContatoRepository contatoRepository;

    @Autowired
    public ContatoServiceImpl(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Override
	public Contato buscarContato(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        return contatoOptional.orElseThrow(() ->
                new ObjetoNaoEcontratoException("Não foi possivel localizar o contato de id " + id));
	}

    @Override
    public List<Contato> buscarTodosContato(String filtro) {
        if (filtro != null && !filtro.isEmpty())
            return contatoRepository.findAllByNomeContains(filtro);
        return contatoRepository.findAll();
    }

    @Override
	public Contato salvarContato(Contato contato) {
        if (contato.getTelefones() != null) {
            for(Telefone telefone : contato.getTelefones()) {
                telefone.setContato(contato);
            }
        }
        return contatoRepository.save(contato);
	}

	@Override
	public Contato atualizarContato(Contato contato) {
        if (contato == null || contato.getId() == null)
            throw new RuntimeException("Id não encontrado");
        if (!contatoRepository.existsById(contato.getId()))
            throw new ObjetoNaoEcontratoException("");

        return contatoRepository.save(contato);
	}

	@Override
	public boolean deletarContato(Long id) {
        if (id == null)
            return false;
        if (!contatoRepository.existsById(id))
            return false;
        contatoRepository.deleteById(id);
        return true;
	}
}
