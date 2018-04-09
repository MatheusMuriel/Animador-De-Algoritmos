package br.unifil.dc.lab2;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.util.ListIterator;

/**
 * Classe para iniciar os filmes.
 * 
 * @author MatheusMuriel
 * @version 09/04/2018
 */
public class Tocador extends JPanel {

    public Tocador(ListIterator<Transparencia> quadrosFilme) {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        carregarFilme(quadrosFilme);
    }
    public Tocador() {
        this(null);
    }
    
    public void carregarFilme(ListIterator<Transparencia> quadrosFilme) {
        this.quadrosFilme = quadrosFilme;
        this.quadroAtual = null;
        numQuadro = 0;
    }
    
    public void avancarFilme() {
        if (quadrosFilme.hasNext()) {
            quadroAtual = quadrosFilme.next();
            numQuadro++;
        }
    }
    
    public void voltarFilme() {
        if (quadrosFilme.hasPrevious()) {
            quadroAtual = quadrosFilme.previous();
            numQuadro--;
        }
    }
    
    public void rebobinarFilme() {
        while (quadrosFilme.hasPrevious()) {
            quadroAtual = quadrosFilme.previous();
            numQuadro--;
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pincel = (Graphics2D) g;
        
        if (quadroAtual != null) {
            quadroAtual.pintar(pincel, this);
        } else {
            String texto = "O Filme ainda não iniciou.";
            FontMetrics fm = pincel.getFontMetrics();
            
            Dimension dim = getSize();
            
            double xString =  (dim.width/2) - (fm.stringWidth(texto)/2);
            double yString = (dim.height/2) - (fm.getHeight()/2);
            pincel.drawString(texto, (int) xString, (int) yString);
        }
        
        String texto = "Quadro 'numQuadro'";
            FontMetrics fm = pincel.getFontMetrics();
            
            Dimension dim = getSize();
            
            double xString =  dim.width - fm.stringWidth(texto) - 3;
            double yString = dim.height - fm.getHeight() + 8;
            pincel.drawString(texto, (int) xString, (int) yString);
    }
    
    private int numQuadro = 0;
    private Transparencia quadroAtual = null;
    private ListIterator<Transparencia> quadrosFilme = null;
}
