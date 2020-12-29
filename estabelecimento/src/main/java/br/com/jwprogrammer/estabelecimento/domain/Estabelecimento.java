package br.com.jwprogrammer.estabelecimento.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Estabelecimento implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nome;
   private String endereco;
   private String telefone;
   @ManyToMany()
   @JoinTable(name = "ESTABELECIMENTO_PROFISSIONAL", joinColumns = @JoinColumn(name = "estabelecimento_id"), inverseJoinColumns = @JoinColumn(name = "profissional_id"))
   private List<Profissional> profissionais = new ArrayList<>();

   public Estabelecimento() {

   }

   public Estabelecimento(Integer id, String nome, String endereco, String telefone, List<Profissional> profissionais) {
      this.id = id;
      this.nome = nome;
      this.endereco = endereco;
      this.telefone = telefone;
      this.profissionais = profissionais;
   }

   public Estabelecimento(Integer id, String nome, String endereco, String telefone) {
      this.id = id;
      this.nome = nome;
      this.endereco = endereco;
      this.telefone = telefone;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getEndereco() {
      return this.endereco;
   }

   public void setEndereco(String endereco) {
      this.endereco = endereco;
   }

   public String getTelefone() {
      return this.telefone;
   }

   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }

   public List<Profissional> getProfissionais() {
      return this.profissionais;
   }

   public void setProfissionais(List<Profissional> profissionais) {
      this.profissionais = profissionais;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + id;
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Estabelecimento other = (Estabelecimento) obj;
      if (id != other.id)
         return false;
      return true;
   }

}