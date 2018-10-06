/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 11724814
 */
public class Campo {

    public int[][] campo;
    List<List<Salao>> caminhos;
    private final int m;
    private final int n;

    public Campo(int m, int n) {
        this.m = m;
        this.n = n;
        campo = new int[m][n];
        caminhos = new ArrayList<>();
    }

    public List<List<Salao>> getCaminhos() {
        return caminhos;
    }

    public void setCaminhos(List<List<Salao>> caminhos) {
        this.caminhos = caminhos;
    }

    public void setCampo(int m, int n, int value) {
        campo[m][n] = value;
    }

    void carregarCaminhos() {
        List<Salao> caminho = new ArrayList<>();
        caminho.add(inicio());
        procurarCaminho(caminho);
    }

    private void procurarCaminho(List<Salao> caminho) {
        Salao atual = caminho.get(caminho.size() - 1);
        List<Salao> vizinhos = vizinhosPossiveis(atual);
        boolean adicionado = false;
        List<Salao> caminhoAtual = caminho.stream().collect(Collectors.toList());
        for (Salao vizinho : vizinhos) {
            if (!caminho.contains(vizinho) && !adicionado) {
                caminho.add(vizinho);
                Salao ultimo = caminho.get(caminho.size() - 1);
                if (saida(ultimo)) {
                    caminhos.add(caminho);
                } else {
                    procurarCaminho(caminho);
                }
                adicionado = true;
            } else if (adicionado) {
                List<Salao> novoCaminho = caminhoAtual.stream().collect(Collectors.toList());
                if (!novoCaminho.contains(vizinho)) {
                    novoCaminho.add(vizinho);
                    Salao ultimo = novoCaminho.get(novoCaminho.size() - 1);
                    if (saida(ultimo)) {
                        caminhos.add(novoCaminho);
                    } else {
                        procurarCaminho(novoCaminho);
                    }
                }
            }
        }
    }

    public boolean saida(Salao salao) {
        return campo[salao.getX()][salao.getY()] == 0;
    }

    public Salao inicio() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                if (campo[i][j] == 3) {
                    return new Salao(i, j);
                }
            }
        }
        return null;
    }

    public boolean verificarSalaoPermitido(Salao salao) {
        return campo[salao.getX()][salao.getY()] != 2;
    }

    private List<Salao> vizinhosPossiveis(Salao atual) {
        List<Salao> vizinhos = new ArrayList<>();
        Salao salao;
        if (atual != null) {
            if (atual.getX() + 1 < m) {
                salao = new Salao(atual.getX() + 1, atual.getY());
                if (verificarSalaoPermitido(salao)) {
                    vizinhos.add(salao);
                }
            }

            if (atual.getX() - 1 >= 0) {
                salao = new Salao(atual.getX() - 1, atual.getY());
                if (verificarSalaoPermitido(salao)) {
                    vizinhos.add(salao);
                }
            }

            if (atual.getY() + 1 < n) {
                salao = new Salao(atual.getX(), atual.getY() + 1);
                if (verificarSalaoPermitido(salao)) {
                    vizinhos.add(salao);
                }
            }

            if (atual.getY() - 1 >= 0) {
                salao = new Salao(atual.getX(), atual.getY() - 1);
                if (verificarSalaoPermitido(salao)) {
                    vizinhos.add(salao);
                }
            }
        }
        return vizinhos;

    }

    public void imprimir() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                System.out.printf("%d ", campo[i][j]);
            }
            System.out.println();
        }
    }

    private void ordernarCaminhos() {
        caminhos.sort((c1, c2) -> Integer.compare(c1.size(), c2.size()));
    }

    int menorCaminho() {
        ordernarCaminhos();
        return caminhos.stream().map(List::size).mapToInt(t -> t - 1).min().orElse(-1);
    }

}
