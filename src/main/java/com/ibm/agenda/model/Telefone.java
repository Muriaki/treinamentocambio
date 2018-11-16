package com.ibm.agenda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude="contato")
public class Telefone {
	@Id
	@GeneratedValue(
			generator="seq_telefone", 
			strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(
			allocationSize = 1,
			name="seq_telefone",
			sequenceName = "seq_telefone")
    private Long id;
    private Short ddd;
    private String numero;
    private String tipoTelefone;
    
    @ManyToOne(targetEntity = Contato.class)
    private Contato contato;
}
