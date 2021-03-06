package br.com.jwprogrammer.estabelecimento.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Profissional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String endereco;
    private String telefoneCelular;
    private String telefoneResidencial;
    private String funcao;
    @ManyToMany(mappedBy = "profissionais", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Estabelecimento> estabelecimentos = new ArrayList<>();

    public Profissional() {

    }

    public Profissional(Integer id, String nome, String endereco, String telefoneCelular, String telefoneResidencial,
            String funcao, List<Estabelecimento> estabelecimentos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefoneCelular = telefoneCelular;
        this.telefoneResidencial = telefoneResidencial;
        this.funcao = funcao;
        this.estabelecimentos = estabelecimentos;
    }

    public Profissional(Integer id, String nome, String endereco, String telefoneCelular, String telefoneResidencial,
            String funcao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefoneCelular = telefoneCelular;
        this.funcao = funcao;
        this.telefoneResidencial = telefoneResidencial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public List<Estabelecimento> getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
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
        Profissional other = (Profissional) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
