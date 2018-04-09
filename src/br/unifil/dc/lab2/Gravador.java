package br.unifil.dc.lab2;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Classe para gravar os passos feitos pelo algoritmo.
 *
 * @author MatheusMuriel
 * @version 09/04/2018
 */
public class Gravador
{
    /**
     * Contrutor que inicia a Lista de gravações como 
     * um ArrayList do tipo Transparencia.
     */
    public Gravador() {
        this.seqGravacoes = new ArrayList<Transparencia>();
    }
    
    /**
     * Metodo que grava a situação atual da lista.
     * @param lista Lista de inteiros.
     * @param nome Nome do momento a ser gravado.
     */
    public void gravarLista(List<Integer> lista, String nome) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        ListaGravada gravacao = new ListaGravada(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Metodo que grava uma posição destacada.
     * @param lista Lista de inteiros.
     * @param i Pocição destacada
     * @param nome  Nome do momento a ser gravado.
     */
    public void gravarIndiceDestacado(List<Integer> lista, int i, String nome) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Metodo que grava uma  marcação com uma cor diferente do metodo gravarIndiceDestacado.
     * @param lista Lista de inteiros.
     * @param i Pocição marcada.
     */
    public void gravarIndiceMarcado(List<Integer> lista, int i) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.LIGHT_GRAY);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Marcado");
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Metodo que grava os indices de uma busca binaria.
     * @param lista Lista de inteiros.
     * @param menor Indice do menor elemento da comparação.
     * @param maior Indice do maior elemento da comparação.
     * @param medio Indice do elemento medio da comparação.
     */
    public void gravarIndiceBuscaBinaria(List<Integer> lista, int menor, int maior, int medio){
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(menor, Color.WHITE);
        cores.set(maior, Color.BLACK);
        cores.set(medio, Color.GRAY);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Marcado");
        seqGravacoes.add(gravacao);
    }

    /**
     * Metodo que grava uma comparação entre dois elementos.
     * @param lista Lista de inteiros.
     * @param i Indice do primeiro elemento da comparação.
     * @param j Indice do segundo elemento da comparação.
     */
    public void gravarComparacaoSimples(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        cores.add(j, Color.GRAY);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Comparação");
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Metodo que grava uma comparação com uma marcação.
     * @param lista Lista de inteiros.
     * @param i Indice do primeiro elemento da comparação.
     * @param j Indice do segundo elemento da comparação.
     * @param m Indice marcado durante a comparação.
     */
    public void gravarComparacaoComMarcacao(List<Integer> lista, int i, int j, int m) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(m, Color.LIGHT_GRAY);
        cores.set(i, Color.GRAY);
        cores.add(j, Color.GRAY);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Comparação");
        seqGravacoes.add(gravacao);
    }
    
    /**
     * Metodo que grava a situação apos uma troca.
     * @param lista Lista de inteiros.
     * @param i Indice do primeiro elemento da troca.
     * @param j Indice do segundo elemento da troca.
     */
    public void gravarPosTrocas(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        cores.set(j, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Pós-troca");
        seqGravacoes.add(gravacao);
    }

    /**
     * Metodo que retorna o filme contendo a Lista de gravações
     * @return  Sequencia de gravações.
     */
    public ListIterator<Transparencia> getFilme() {
        return seqGravacoes.listIterator();
    }

    /**
     * Metodo que cria uma lista para armazenar as cores.
     * @param numElems  Inteiro corespondente a quantidade de elementos da lista.
     * @return Lista para armazenar as cores.
     */
    private static List<Color> novaListaColors(int numElems) {
        ArrayList<Color> lista = new ArrayList<>(numElems);
        for (; numElems > 0; numElems--) lista.add(null);
        return lista;
    }

    /**
     * Lista do tipo Transparencia para armazenar a lista de gravações.
     */
    private List<Transparencia> seqGravacoes;
}
