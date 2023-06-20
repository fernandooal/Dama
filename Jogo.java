package br.com.unicuritiba.dama;

import javax.swing.JOptionPane;

import br.com.unicuritiba.dama.model.*;

public class Jogo {

	public static void main(String[] args) {
		
		Jogador jogador1 = new Jogador();
		Jogador jogador2 = new Jogador();
		
		jogador1.setNome(JOptionPane.showInputDialog("Digite o nome do jogador 1 (peças vermelhas):" ));
		jogador2.setNome(JOptionPane.showInputDialog("Digite o nome do jogador 2 (peças pretas):" ));
		
		Tabuleiro.comecarJogo(jogador1, jogador2);
	}
}