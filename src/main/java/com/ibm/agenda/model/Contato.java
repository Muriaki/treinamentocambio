package com.ibm.agenda.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Contato {
	@Id
	@GeneratedValue(
			generator="seq_contato",
			strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(
			allocationSize = 1,
			name="seq_contato",
			sequenceName = "seq_contato")
	private Long id;
	
	private String nome;
	
	@OneToMany(
			mappedBy = "contato", 
			targetEntity = Telefone.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Telefone> telefones;
}
