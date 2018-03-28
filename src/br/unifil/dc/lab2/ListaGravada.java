package br.unifil.dc.lab2;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
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
        //pincel.drawRect(200,200,10,10);
        System.out.println("Dentro do Lista Gravada " + lista);
        int maiorElemento = 0;
        for(int i = 0; i < lista.size(); i++){
            if (lista.get(i) > maiorElemento){
                maiorElemento = lista.get(i);
            }
        }

        //comparando elementos
        List<Float> listaProporcoes = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++){
            // gera um numero quebrado referente a proporção
            float listTemp = lista.get(i);
            float temp = listTemp / maiorElemento;
            listaProporcoes.add(i, temp);
        }

        //Gera em pixel os tamanhos
        List<Integer> listaPixels = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++){
            int temp = (listaProporcoes.get(i)).intValue();
            float px = 300 * listaProporcoes.get(i);
            int aux = (int) px;
            listaPixels.add(i, aux);
        }

        //exibe na tela
        final int x = dim.width;
        final int y = dim.height;

        int xBarra = 20;
        //Fazer um relacao de aumento
        int quadroInterno = y - 40;
        int larguraBlocos = 72;
        int espaçosBlocos = 50;
        int numeroDeBlocos = lista.size();

        //pincel.drawRect(100,100,30, listaPixels.get(1));
        for (int i = 0; i < lista.size(); i++){
            int yBarra = y - 100 - listaPixels.get(i);
            pincel.drawRect(xBarra, yBarra, 72, listaPixels.get(i));
            String aux = Integer.toString(lista.get(i));
            pincel.drawString(aux, xBarra + 30, y - 85);
            xBarra += 72 + 50; //Afasta as colunas entre si
        }

        //throw new RuntimeException("Funcionalidade ainda não implementada pelo aluno");
    }
    
    
    private List<Integer> lista;
    private List<Color> coresIndices;
    private String nome;
}
