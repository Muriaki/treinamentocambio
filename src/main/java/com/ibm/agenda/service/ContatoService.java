package com.ibm.agenda.service;

import com.ibm.agenda.model.Contato;

import java.util.List;

public interface ContatoService {
    Contato buscarContato(Long id);
    List<Contato> buscarTodosContato(String filtro);
    Contato salvarContato(Contato contato);
    Contato atualizarContato(Contato contato);
    boolean deletarContato(Long id);
}
