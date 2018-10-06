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
            m = scanner.nextInt();
        } while (m < 0 || m > 10);
        int n;
        do {
            n = scanner.nextInt();
        } while (n < 0 || n > 10);
        Campo campo = new Campo(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                campo.setCampo(i, j, scanner.nextInt());
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
