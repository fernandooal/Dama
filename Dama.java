package br.com.unicuritiba.dama.model;

import java.util.LinkedList;

//classe filha da classe Peca
public class Dama extends Peca {

	// construtor
	public Dama(int xPeca, int yPeca, boolean vermelho, LinkedList<Peca> pecaLista, boolean dama, boolean comeuPeca) {
		super(xPeca, yPeca, vermelho, pecaLista, dama, comeuPeca);
	}

	// método para movimentar a dama (diferente de uma peça comum)
	public void mexerDama(int xPeca, int yPeca) {

		// calculando a diferença do x e do y da origem e de onde a peça foi solta
		int diffX = Math.abs(this.xPeca - xPeca);
		int diffY = Math.abs(this.yPeca - yPeca);

		// diferenciando por cor pra não ser possivel comer peças da mesma cor
		if (isVermelho() == true) {

			// condição para verificar se a peça pode andar (o x e o y na dama sempre andam
			// juntos na movimentação)
			if (diffX == diffY) {
				int direcaoX = Integer.compare(xPeca, this.xPeca);
				int direcaoY = Integer.compare(yPeca, this.yPeca);
				// identificando as coordenadas da peça imediatamente atrás do local final da
				// movimentação para removê-la
				int pecaComidaX = this.xPeca + direcaoX;
				int pecaComidaY = this.yPeca + direcaoY;

				boolean todasCasasVazias = true;

				// verificando se a peça comida nao esta no mesmo lugar da peça movimentada
				while (pecaComidaX != xPeca && pecaComidaY != yPeca) {
					boolean casaVazia = true;

					// passando pela lista de peças inteira ate achar alguma que esteja nas
					// coordenadas da pecaComidaX e Y
					for (Peca p : pecaLista) {
						if (p.getxPeca() == pecaComidaX && p.getyPeca() == pecaComidaY) {
							// se nao for vermelho, sera removida
							if (p.isVermelho() == false) {
								p.removerPeca();
								p.setComeuPeca(true);
							} else {
								// caso seja vermelha, colocando o boolean falso para cair no caso que reseta a
								// posição da peça.
								todasCasasVazias = false;
							}
							casaVazia = false;
							break;
						}
					}

					// se a casa não estiver vazia, quebra o while
					if (!casaVazia) {
						break;
					}

					// adicionando as coordenadas na peça comida
					pecaComidaX += direcaoX;
					pecaComidaY += direcaoY;
				}

				// depois do while, verifica se todas as casas estao vazias para movimentação
				if (todasCasasVazias) {
					this.xPeca = xPeca;
					this.yPeca = yPeca;
					x = xPeca * 64;
					y = yPeca * 64;
				} else {
					// se nao, reseta a posição
					resetarPosicao();
				}
			} else {
				// caso o x e o y de movimentação nao sejam iguais, será resetado (movimentação
				// inválida);
				resetarPosicao();
			}
			// repetindo todo o processo, mas para o caso das peças serem pretas.
		} else {
			if (diffX == diffY) {
				int direcaoX = Integer.compare(xPeca, this.xPeca);
				int direcaoY = Integer.compare(yPeca, this.yPeca);
				int pecaComidaX = this.xPeca + direcaoX;
				int pecaComidaY = this.yPeca + direcaoY;

				boolean todasCasasVazias = true;

				while (pecaComidaX != xPeca && pecaComidaY != yPeca) {
					boolean casaVazia = true;

					for (Peca p : pecaLista) {
						if (p.getxPeca() == pecaComidaX && p.getyPeca() == pecaComidaY) {
							if (p.isVermelho()) {
								p.removerPeca();
								p.setComeuPeca(true);
							} else {
								todasCasasVazias = false;
							}
							casaVazia = false;
							break;
						}
					}

					if (!casaVazia) {
						break;
					}

					pecaComidaX += direcaoX;
					pecaComidaY += direcaoY;
				}

				if (todasCasasVazias) {
					this.xPeca = xPeca;
					this.yPeca = yPeca;
					x = xPeca * 64;
					y = yPeca * 64;
				} else {
					resetarPosicao();
				}
			} else {
				resetarPosicao();
			}
		}
	}
}
