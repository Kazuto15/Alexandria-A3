/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author Kazuto
 */
public class Livro {
    private int id;
    private String nome;
    private String autor;
    private int idTipo;
    private String Tipo;
    private String feedback;
    private int usuarioId;

    
    public Livro() {
    }

    public Livro(String nome, String autor, int idTipo, String Tipo, String feedback, int usuarioId) {
        this.nome = nome;
        this.autor = autor;
        this.idTipo = idTipo;
        this.Tipo = Tipo;
        this.feedback = feedback; 
        this.usuarioId = usuarioId;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    public String getFeedback(){
    return feedback;
    }
    
    public void setFeedback(String feedback){
    this.feedback = feedback;
    }
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    
}
