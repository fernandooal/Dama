package br.com.unicuritiba.dama.model;

import java.awt.*;
import java.util.LinkedList;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tabuleiro {

	// atributos
	public static LinkedList<Peca> pecaLista = new LinkedList<>();
	public static Peca pecaSelecionada = null;
	int pecasVermelhasRemovidas = 0;
	int pecasPretasRemovidas = 0;

	// metodo para começar o jogo
	public static void comecarJogo(Jogador jogador1, Jogador jogador2) {

		// setup do frame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Turno de " + jogador1.getNome());
		frame.setSize(528, 550);

		// criando as pecas e aplicando os atributos para cada uma
		Peca v1 = new Dama(1, 0, true, pecaLista, false, false);
		Peca v2 = new Dama(3, 0, true, pecaLista, false, false);
		Peca v3 = new Dama(5, 0, true, pecaLista, false, false);
		Peca v4 = new Dama(7, 0, true, pecaLista, false, false);
		Peca v5 = new Dama(0, 1, true, pecaLista, false, false);
		Peca v6 = new Dama(2, 1, true, pecaLista, false, false);
		Peca v7 = new Dama(4, 1, true, pecaLista, false, false);
		Peca v8 = new Dama(6, 1, true, pecaLista, false, false);
		Peca v9 = new Dama(1, 2, true, pecaLista, false, false);
		Peca v10 = new Dama(3, 2, true, pecaLista, false, false);
		Peca v11 = new Dama(5, 2, true, pecaLista, false, false);
		Peca v12 = new Dama(7, 2, true, pecaLista, false, false);
		Peca p1 = new Dama(0, 7, false, pecaLista, false, false);
		Peca p2 = new Dama(2, 7, false, pecaLista, false, false);
		Peca p3 = new Dama(4, 7, false, pecaLista, false, false);
		Peca p4 = new Dama(6, 7, false, pecaLista, false, false);
		Peca p5 = new Dama(1, 6, false, pecaLista, false, false);
		Peca p6 = new Dama(3, 6, false, pecaLista, false, false);
		Peca p7 = new Dama(5, 6, false, pecaLista, false, false);
		Peca p8 = new Dama(7, 6, false, pecaLista, false, false);
		Peca p9 = new Dama(0, 5, false, pecaLista, false, false);
		Peca p10 = new Dama(2, 5, false, pecaLista, false, false);
		Peca p11 = new Dama(4, 5, false, pecaLista, false, false);
		Peca p12 = new Dama(6, 5, false, pecaLista, false, false);

		// criando as casas com JPanel
		JPanel panelCasas = new JPanel() {
			@Override
			public void paint(Graphics g) {
				boolean claro = true;
				// criando um loop que passa pelas 8 casas horizontais e depois pelas 8
				// verticais, invertendo a ordem de pintura para criar o xadrez do tabuleiro
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						if (claro) {
							g.setColor(new Color(240, 217, 181));
						} else {
							g.setColor(new Color(181, 136, 99));
						}
						int casaX = x * 64 - 32; // coordenada X ajustada para centralizar
						int casaY = y * 64; // coordenada Y ajustada para centralizar
						g.fillRect(casaX + 32, casaY, 64, 64);
						claro = !claro;
					}
					claro = !claro;
				} // colocando as pecas nas casas por coordenadas
				for (Peca p : pecaLista) {
					if (p.isVermelho() == true) {
						g.setColor(Color.red);
						g.fillOval(p.getX(), p.getY(), 64, 64);
					} else {
						g.setColor(Color.black);
						g.fillOval(p.getX(), p.getY(), 64, 64);
					}
					// alterando o gráfico da peça que for dama, colocando um retângulo branco no
					// meio dela para diferenciar
					if (p.isDama()) {
						g.setColor(Color.WHITE);
						g.fillRect(p.getX() + 22, p.getY() + 22, 20, 20);
					}
				}
			}
		};

		// adicionando o panel no frame
		frame.add(panelCasas);

		// implementando o mouse listener para movimentação das peças
		frame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// caso a peça selecionada não seja inexistente, a peça seguirá o mouse de
				// acordo com as coordenadas
				if (pecaSelecionada != null) {
					pecaSelecionada.setX(e.getX() - 32);
					pecaSelecionada.setY(e.getY() - 64);
					frame.repaint();
				}
			}
		});

		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// quando soltar a peça, caso ela exista, deverá ficar sobre a casa que está
				// quando for solta
				if (pecaSelecionada != null) {
					// ajustando a coordenada da casa para o centro
					int tamanhoCasa = 64;
					int xPeca = (e.getX() - tamanhoCasa / 2 + 32) / tamanhoCasa;
					int yPeca = (e.getY() - tamanhoCasa / 2) / tamanhoCasa;

					// chamando os métodos das peças e das damas para realmente movimentar a peça
					if (pecaSelecionada.isDama()) {
						pecaSelecionada.mexerDama(xPeca, yPeca);
					} else {
						pecaSelecionada.mexerPeca(xPeca, yPeca);
					}

					// verificar se a peça chegou na última fileira para virar dama
					if (pecaSelecionada.isDama() == false && pecaSelecionada.isVermelho()
							&& pecaSelecionada.getyPeca() == 7) {
						pecaSelecionada.setDama(true);
					} else if (pecaSelecionada.isDama() == false && pecaSelecionada.isVermelho() == false
							&& pecaSelecionada.getyPeca() == 0) {
						pecaSelecionada.setDama(true);
					}

					frame.repaint();

					// alterando o turno ao final de cada movimentação
					if (pecaSelecionada.isVermelho() && frame.getTitle().equals("Turno de " + jogador1.getNome())) {
						frame.setTitle("Turno de " + jogador2.getNome());
					} else {
						frame.setTitle("Turno de " + jogador1.getNome());
					}

					pecaSelecionada.setComeuPeca(false);

					// checando se alguém ganhou no final
					checarVitoria(jogador1, jogador2);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ajustando as coordenadas x e y ao pegar a peça
				int x = e.getX() - 16;
				int y = e.getY() - 32;

				pecaSelecionada = pegarPeca(x, y);

				// proibindo pegar a peça do outro jogador durante seu turno
				if (frame.getTitle().equals("Turno de " + jogador1.getNome()) && pecaSelecionada != null
						&& !pecaSelecionada.isVermelho()) {
					pecaSelecionada = null;
				} else if (frame.getTitle().equals("Turno de " + jogador2.getNome()) && pecaSelecionada != null
						&& pecaSelecionada.isVermelho()) {
					pecaSelecionada = null;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		// print do tabuleiro
		frame.setVisible(true);
	}

	// metodo para verificar a vitória após cada jogada
	public static void checarVitoria(Jogador jogador1, Jogador jogador2) {
		int pecasPretas = 0;
		int pecasVermelhas = 0;

		// cada vez que for executado, o método passará pela lista inteira de peças
		for (Peca p : pecaLista) {
			if (p.isVermelho()) {
				pecasVermelhas++;
			} else {
				pecasPretas++;
			}
		}

		// caso não tenha nenhuma peça de determinada cor, o jogo acabará e será
		// anunciado o vencedor
		if (pecasPretas == 0) {
			JOptionPane.showMessageDialog(null, "Parabéns " + jogador1.getNome() + ", você ganhou!");
		} else if (pecasVermelhas == 0) {
			JOptionPane.showMessageDialog(null, "Parabéns " + jogador2.getNome() + ", você ganhou!");
		}
	}

	// método utilizado para pegar a peça
	public static Peca pegarPeca(int x, int y) {

		// igualando as coordenadas do layout a da peça
		int xPeca = x / 64;
		int yPeca = y / 64;

		// passando pela lista inteira de peças ate achar aquela que foi selecionada com
		// base nas coordenadas
		for (Peca p : pecaLista) {
			if (p.getxPeca() == xPeca && p.getyPeca() == yPeca) {
				return p;
			}
		}

		return null;
	}
}
