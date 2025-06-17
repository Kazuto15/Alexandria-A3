/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Kazuto
 */
public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private LocalDate dataNasc;
    private String tipoFav1;
    private String tipoFav2;
        

    public Usuario() {
    }

    public Usuario(String nome, String senha, LocalDate dataNasc, String tipoFav1, String tipoFav2) {
        this.nome = nome;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.tipoFav1 = tipoFav1;
        this.tipoFav2 = tipoFav2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    public int getIdade(){
        if (dataNasc == null)return 0;
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }
     public String getTipoFav1() {
        return tipoFav1;
    }

    public void setTipoFav1(String tipoFav1) {
        this.tipoFav1 = tipoFav1;
    }

    public String getTipoFav2() {
        return tipoFav2;
    }

    public void setTipoFav2(String tipoFav2) {
        this.tipoFav2 = tipoFav2;
    }
    
}
