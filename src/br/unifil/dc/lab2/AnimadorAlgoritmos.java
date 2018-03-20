package br.unifil.dc.lab2;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;


public class AnimadorAlgoritmos extends JFrame {
    
    /**
     * O construtor do aplicativo AnimadorAlgoritmos. É aqui que todos os elementos da interface gráfica são
     * construídos, configurados e posicionados. Por enquanto, aqui também é feito o tratamento de 
     * eventos (input de usuário através de mouse e teclado), utilizando métodos anônimos (lambda).
     * 
     * @see javax.swing.JFrame
     */
    public AnimadorAlgoritmos() {
        
        // Cria e configura botões
        btnCarregar = new JButton("Carregar");
        btnCarregar.addActionListener((e) -> onBtnCarregarPressionado());
        
        btnProx = new JButton("Prox");
        btnProx.setEnabled(false);
        btnProx.addActionListener((e) -> onBtnProxPressionado());
        
        btnAnt = new JButton("Ant");
        btnAnt.setEnabled(false);
        btnAnt.addActionListener((e) -> onBtnAntPressionado());
        
        // Cria e configura o campo de seleção de algoritmos animados
        String algsAnimados[] = {
            "Lista estática",
            "Busca sequencial", "Busca binária",
            "Bolha", "Seleção", "Inserção", "Mergesort", "Quicksort" };
        boxListaAlgoritmos = new JComboBox<String>(algsAnimados);
        boxListaAlgoritmos.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String item = (String) e.getItem();
                novoAlgoritmoSelecionado(item);
            }
        });
        
        final int COMPRIMENTO_ENTRADA_LISTA = 20;
        txfEntradaValores = new JTextField("", COMPRIMENTO_ENTRADA_LISTA);
        
        final int COMPRIMENTO_ENTRADA_CHAVE_BUSCA = 3;
        txfEntradaChaveBusca = new JTextField("", COMPRIMENTO_ENTRADA_CHAVE_BUSCA);
        
        // Campo para abrigar e organizar os botões e campos de entrada
        JPanel pnlBotoes = new JPanel(new FlowLayout());
        pnlBotoes.add(new JLabel("Valores:"));
        pnlBotoes.add(txfEntradaValores);
        pnlBotoes.add(new JLabel("Chave:"));
        pnlBotoes.add(txfEntradaChaveBusca);
        pnlBotoes.add(boxListaAlgoritmos);
        pnlBotoes.add(btnCarregar);
        pnlBotoes.add(btnAnt);
        pnlBotoes.add(btnProx);
        
        // Cria e configura a tela do desenhista
        tela = new Tocador();
        tela.setPreferredSize(new Dimension(800, 600));

        // Container que organiza o posicionamento dos botões e da tela
        Container organizacao = getContentPane();
        organizacao.setLayout(new BorderLayout());
        organizacao.add(tela, BorderLayout.CENTER);
        organizacao.add(pnlBotoes, BorderLayout.SOUTH);

        // Configurações de comportamento do aplicativo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AnimadorAlgoritmos - Lab. Comp. II");
        pack();
        setVisible(true);
        requestFocus();
    }
    
    public void onBtnCarregarPressionado() {
        // Verifica o desenho escolhido no ComboBox e repassa à tela para pintura
        List<Integer> valores = textoParaLista(txfEntradaValores.getText());
        String algoritmo = (String) boxListaAlgoritmos.getSelectedItem();
        
        Gravador novoFilme = null;
        switch (algoritmo) {
            case "Lista estática":
                novoFilme = AlgoritmosAnimados.listaEstatica(valores);
                break;
            case "Busca sequencial":
                int chave = Integer.parseInt(txfEntradaChaveBusca.getText());
                novoFilme = AlgoritmosAnimados.buscaSequencial(valores, chave);
                break;
            
            case "Bolha":
                novoFilme = AlgoritmosAnimados.ordenarPorBolha(valores);
                break;
                
            default:
                throw new UnsupportedOperationException("Funcionalidade ainda não implementada pelo aluno");
        }
        
        
        if (novoFilme != null) {
            tela.carregarFilme(novoFilme.getFilme());
            btnProx.setEnabled(true);
            btnAnt.setEnabled(true);
        }
        
        onBtnProxPressionado();
    }
    
    public void onBtnProxPressionado() {
        tela.avancarFilme();
        tela.repaint();
        requestFocusInWindow();
    }
    
    public void onBtnAntPressionado() {    
        tela.voltarFilme();
        tela.repaint();
        requestFocusInWindow();
    }
    
    public void novoAlgoritmoSelecionado(String alg) {
        if (alg.equals("Busca Sequencial") || alg.equals("Busca Binária")) {
            txfEntradaChaveBusca.setEnabled(true);
        } else {
            txfEntradaChaveBusca.setEnabled(false);
        }
    }
    
    
    /**
     * Ponto de início do programa. Simplesmente informa que é um aplicativo gráfico e passa o
     * controle para o construtor do método. Utiliza uma segunda thread para isso.
     * 
     * @param args Argumentos recebidos da linha de comando.
     */
    public static void main(String[] args) {
        // Código que inicializa o aplicativo gráfico
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnimadorAlgoritmos();
            }
        });
    }
    
    
    /**
     * Método auxiliar para a classe que converte para uma lista de Integers uma sequencia de
     * valores em String, separados por ",". Valores não numéricos são considerados como 0.
     * 
     * @param textoValores String com valores separados por ",".
     * @return Lista de inteiros de acordo com a String de entrada.
     */
    private static List<Integer> textoParaLista(String textoValores) {
        String[] numerosTxt = textoValores.split(",");
        List<Integer> lista = new ArrayList<>(numerosTxt.length);

        for (String numTxt : numerosTxt) {
            try {
                lista.add(new Integer(numTxt));
            } catch (NumberFormatException nfe) {
                String entradaErrada = nfe.getMessage();
                int posIni = entradaErrada.indexOf("\"") + 1;
                int posFim = entradaErrada.lastIndexOf("\"");
                entradaErrada = entradaErrada.substring(posIni, posFim);
                
                System.err.println("Ignorando entrada '" + entradaErrada + "'. Utilizado valor 0.");
                lista.add(0);
            }
        }
        
        return lista;
    }
    
    // Elementos (widgets) da nossa interface
    private Tocador tela;
    private JButton btnCarregar, btnProx, btnAnt;
    private JComboBox<String> boxListaAlgoritmos;
    private JTextField txfEntradaValores;
    private JTextField txfEntradaChaveBusca;
}