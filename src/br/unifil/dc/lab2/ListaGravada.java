package br.unifil.dc.lab2;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;

/**
 * Write a description of class ListaGravada here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaGravada implements Transparencia
{
    /**
     * Constructor for objects of class ListaGravada
     */
    public ListaGravada(List<Integer> lista, List<Color> coresIndices, String nome) {
        this.lista = lista;
        this.nome = nome;
        this.coresIndices = coresIndices;
    }
    
    
    public void pintar(Graphics2D pincel, JPanel contexto) {
        Dimension dim = contexto.getSize();

        throw new RuntimeException("Funcionalidade ainda não implementada pelo aluno");
    }
    
    
    private List<Integer> lista;
    private List<Color> coresIndices;
    private String nome;
}
