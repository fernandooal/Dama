package br.com.unicuritiba.dama.model;

import java.util.LinkedList;

public class Dama extends Peca {

	public Dama(int xPeca, int yPeca, boolean vermelho, LinkedList<Peca> pecaLista, boolean dama, boolean comeuPeca) {
		super(xPeca, yPeca, vermelho, pecaLista, dama, comeuPeca);
	}
	
	public void mexerDama(int xPeca, int yPeca) {
		int diffX = Math.abs(this.xPeca - xPeca);
		int diffY = Math.abs(this.yPeca - yPeca);

		if (isVermelho() == true) {

			if (diffX == diffY) {
				int directionX = Integer.compare(xPeca, this.xPeca);
				int directionY = Integer.compare(yPeca, this.yPeca);
				int pecaComidaX = this.xPeca + directionX;
				int pecaComidaY = this.yPeca + directionY;
				
				boolean todasCasasVazias = true;
				
				while (pecaComidaX != xPeca && pecaComidaY != yPeca) {
					boolean casaVazia = true;

					for (Peca p : pecaLista) {
						if (p.getxPeca() == pecaComidaX && p.getyPeca() == pecaComidaY) {
							if(p.isVermelho() == false) {
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

					pecaComidaX += directionX;
					pecaComidaY += directionY;
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
		} else {
			if (diffX == diffY) {
				int directionX = Integer.compare(xPeca, this.xPeca);
				int directionY = Integer.compare(yPeca, this.yPeca);
				int pecaComidaX = this.xPeca + directionX;
				int pecaComidaY = this.yPeca + directionY;
				
				boolean todasCasasVazias = true;
				
				while (pecaComidaX != xPeca && pecaComidaY != yPeca) {
					boolean casaVazia = true;

					for (Peca p : pecaLista) {
						if (p.getxPeca() == pecaComidaX && p.getyPeca() == pecaComidaY) {
							if(p.isVermelho()) {
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

					pecaComidaX += directionX;
					pecaComidaY += directionY;
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