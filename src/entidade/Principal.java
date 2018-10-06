/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.Scanner;

/**
 *
 * @author 11724814
 */
public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m;
        do {
            System.out.println("Digite quantidade de linhas");
            m = scanner.nextInt();
        } while (m <= 0 || m > 10);
        int n;
        do {
            System.out.println("Digite quantidade de colunas");
            n = scanner.nextInt();
        } while (n <= 0 || n > 10);
        Campo campo = new Campo(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value;
                do {
                    System.out.println("Digite valor do elemento no par ordenado (" + (i + 1) + "," + (j + 1) + ")");
                    value = scanner.nextInt();
                } while (value < 0 || value > 3);
                campo.setCampo(i, j, value);
            }
        }
        campo.imprimir();
        campo.carregarCaminhos();
        if (campo.getCaminhos().isEmpty()) {
            System.out.println("Não foi encontrado caminhos");
        } else {
            System.out.println(campo.menorCaminho());
        }
    }
}
