/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Vixtor61 <00198117@uca.edu.sv>
 */
public class Movie {
    private int idMovie;
    private String nombre;
    private String diretor;
    private String pais;
    private String Clasificacion;
    private int año;
    private boolean en_proyeccion;
    
    
    public Movie(){}

    public Movie(int idMovie, String nombre, String diretor, String pais, String Clasificacion, int año, boolean en_proyeccion) {
        this.idMovie = idMovie;
        this.nombre = nombre;
        this.diretor = diretor;
        this.pais = pais;
        this.Clasificacion = Clasificacion;
        this.año = año;
        this.en_proyeccion = en_proyeccion;
    }

    public Movie(String nombre, String diretor, String pais, String Clasificacion, int año, boolean en_proyeccion) {
        this.nombre = nombre;
        this.diretor = diretor;
        this.pais = pais;
        this.Clasificacion = Clasificacion;
        this.año = año;
        this.en_proyeccion = en_proyeccion;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String Clasificacion) {
        this.Clasificacion = Clasificacion;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean isEn_proyeccion() {
        return en_proyeccion;
    }

    public void setEn_proyeccion(boolean en_proyeccion) {
        this.en_proyeccion = en_proyeccion;
    }

    
    
    
}




