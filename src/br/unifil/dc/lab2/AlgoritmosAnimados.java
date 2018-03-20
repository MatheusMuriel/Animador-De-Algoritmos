package br.unifil.dc.lab2;

import java.util.List;
import java.util.ListIterator;

/**
 * Write a description of class AlgoritmosAnimados here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlgoritmosAnimados
{
    public static Gravador listaEstatica(List<Integer> valores) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Valores da lista imutável");
        return anim;
    }

    public static Gravador buscaSequencial(List<Integer> valores, int chave) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Inicio de busca sequencial");
        
        int i = 0;
        anim.gravarIndiceDestacado(valores, i, "Busca sequencial");
        while (i < valores.size() && valores.get(i) != chave) {
            i++;
            anim.gravarIndiceDestacado(valores, i, "Busca sequencial");
        }
        
        if (i < valores.size()) {
            anim.gravarIndiceDestacado(valores, i, "Chave encontrada");
        } else {
            anim.gravarLista(valores, "Chave não encontrada");
        }
        
        return anim;
    }
    
    
    public static Gravador ordenarPorBolha(List<Integer> valores) {
        //Gravador anim = new Gravador();
        //anim.gravarLista(valores, "Disposição inicial");
        
        throw new UnsupportedOperationException("Funcionalidade ainda não implementada pelo aluno");
        
        //anim.gravarLista(valores, "Disposição final");
        //return anim;
    }
}