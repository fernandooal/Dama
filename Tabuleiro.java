package br.com.unicuritiba.dama.model;

import java.awt.*;
import java.util.LinkedList;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tabuleiro {

	public static LinkedList<Peca> pecaLista = new LinkedList<>();
	public static Peca pecaSelecionada = null;
	int pecasVermelhasRemovidas = 0;
	int pecasPretasRemovidas = 0;
	
	public static void comecarJogo(Jogador jogador1, Jogador jogador2) {
		
		JPanel panelCasas;
		
		// setup do frame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Turno de " + jogador1.getNome());
		frame.setSize(528, 550);

		// criando as pecas e aplicando as coordenadas para cada
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
		panelCasas = new JPanel() {
			@Override
			public void paint(Graphics g) {
				boolean claro = true;
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						if (claro) {
							g.setColor(new Color(240, 217, 181));
						} else {
							g.setColor(new Color(181, 136, 99));
						}
						g.fillRect(x * 64, y * 64, 64, 64);
						claro = !claro;
					}
					claro = !claro;
				} // Colocando as pecas nas casas por coordenadas
				for (Peca p : pecaLista) {
					if (p.isVermelho() == true) {
						g.setColor(Color.red);
						g.fillOval(p.getX(), p.getY(), 64, 64);
					} else {
						g.setColor(Color.black);
						g.fillOval(p.getX(), p.getY(), 64, 64);
					}
					if (p.isDama()) {
		                g.setColor(Color.WHITE);
		                g.fillRect(p.getX() + 22, p.getY() + 22, 20, 20);
		            }
				}
			}
		};
		

		// adicionando o panel no frame
		frame.add(panelCasas);

		// implementando o mouse listener

		frame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				
			}

			@Override
			public void mouseDragged(MouseEvent e) {

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
			    if (pecaSelecionada != null) {
			        if (pecaSelecionada.isDama()) {
			        	pecaSelecionada.mexerDama(e.getX() / 64, e.getY() / 64);
			        } else {
			        	pecaSelecionada.mexerPeca(e.getX() / 64, e.getY() / 64);
			        }
			        
			        
			        // Verificar se a peça chegou à última fileira e transformá-la em uma dama
			        if(pecaSelecionada.isDama() == false && pecaSelecionada.isVermelho() && pecaSelecionada.getyPeca() == 7) {
			        	pecaSelecionada.setDama(true);
			        } else if(pecaSelecionada.isDama() == false && pecaSelecionada.isVermelho() == false && pecaSelecionada.getyPeca() == 0) {
			        	pecaSelecionada.setDama(true);
			        }
			    }

			    frame.repaint();
			    
			    if(pecaSelecionada.isVermelho() && frame.getTitle().equals("Turno de " + jogador1.getNome())) {
			    	frame.setTitle("Turno de " + jogador2.getNome());
			    } else {
			    	frame.setTitle("Turno de " + jogador1.getNome());
			    } 
			    
			    pecaSelecionada.setComeuPeca(false);
			    
			    checarVitoria(jogador1, jogador2);
			    
			}

			@Override
			public void mousePressed(MouseEvent e) {

				pecaSelecionada = pegarPeca(e.getX(), e.getY());
			
				if(frame.getTitle().equals("Turno de " + jogador1.getNome()) && pecaSelecionada.isVermelho() == false) {
					pecaSelecionada = null ;
				} else if(frame.getTitle().equals("Turno de " + jogador2.getNome()) && pecaSelecionada.isVermelho()) {
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
	
	public static void checarVitoria(Jogador jogador1, Jogador jogador2) {
	    int pecasPretas = 0;
	    int pecasVermelhas = 0;

	    for (Peca p : pecaLista) {
	        if (p.isVermelho()) {
	            pecasVermelhas++;
	        } else {
	            pecasPretas++;
	        }
	    }

	    if (pecasPretas == 0) {
	        JOptionPane.showMessageDialog(null, "Parabéns " + jogador1.getNome() + ", você ganhou!");
	    } else if (pecasVermelhas == 0) {
	        JOptionPane.showMessageDialog(null, "Parabéns " + jogador2.getNome() + ", você ganhou!");
	    }
	}
	
	public static Peca pegarPeca(int x, int y) {
		int xPeca = x / 64;
		int yPeca = y / 64;

		for (Peca p : pecaLista) {
			if (p.getxPeca() == xPeca && p.getyPeca() == yPeca) {
				return p;
			}
		}

		return null;
	}
}