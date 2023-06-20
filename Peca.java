package br.com.unicuritiba.dama.model;

import java.util.LinkedList;

public abstract class Peca {
	protected int xPeca;
	protected int yPeca;
	protected int x;
	protected int y;
	protected boolean vermelho;
	public static LinkedList<Peca> pecaLista;
	protected boolean dama;
	private boolean comeuPeca;

	public Peca(int xPeca, int yPeca, boolean vermelho, LinkedList<Peca> pecaLista, boolean dama, boolean comeuPeca) {
		this.vermelho = vermelho;
		this.xPeca = xPeca;
		this.yPeca = yPeca;
		x = xPeca * 64;
		y = yPeca * 64;
		this.pecaLista = pecaLista;
		this.dama = dama;
		this.comeuPeca = comeuPeca;

		pecaLista.add(this);
	}

	public boolean isComeuPeca() {
		return comeuPeca;
	}

	public void setComeuPeca(boolean comeuPeca) {
		this.comeuPeca = comeuPeca;
	}

	public boolean isDama() {
		return dama;
	}

	public void setDama(boolean dama) {
		this.dama = dama;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxPeca() {
		return xPeca;
	}

	public void setxPeca(int xPeca) {
		this.xPeca = xPeca;
	}

	public int getyPeca() {
		return yPeca;
	}

	public void setyPeca(int yPeca) {
		this.yPeca = yPeca;
	}

	public boolean isVermelho() {
		return vermelho;
	}

	public void setVermelho(boolean vermelho) {
		this.vermelho = vermelho;
	}

	public LinkedList<Peca> getPecaLista() {
		return pecaLista;
	}

	public void setPecaLista(LinkedList<Peca> pecaLista) {
		this.pecaLista = pecaLista;
	}

	public final void mexerPeca(int xPeca, int yPeca) {
		// Verifica se o movimento é diagonal (uma casa na vertical e uma casa na
		// horizontal)
		if (isVermelho() == true) {
			if (Math.abs(this.xPeca - xPeca) == 1 && (this.yPeca - yPeca) == -1) {
				boolean casaVazia = true;
				for (Peca p : pecaLista) {
					if (p.getxPeca() == xPeca && p.getyPeca() == yPeca) {
						casaVazia = false;
						break;
					}
				}

				if (casaVazia) {
					this.xPeca = xPeca;
					this.yPeca = yPeca;
					x = xPeca * 64;
					y = yPeca * 64;
				} else {
					resetarPosicao();
				}
			} else if (Math.abs(this.xPeca - xPeca) == 2 && Math.abs(this.yPeca - yPeca) == 2) {
				boolean casaVazia = true;
				int pecaComidaX = (this.xPeca + xPeca) / 2; // Coordenada X da peça a ser comida
				int pecaComidaY = (this.yPeca + yPeca) / 2; // Coordenada Y da peça a ser comida

				for (Peca p : pecaLista) {
					if (p.getxPeca() == pecaComidaX && p.getyPeca() == pecaComidaY) {
						if(p.isVermelho() == false) {
							p.removerPeca(); // Remove a peça que será comida
							p.setComeuPeca(true);;
						} else {
							casaVazia = false;
						}
						break;
					}
				}

				if (casaVazia) {
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
			if (Math.abs(this.xPeca - xPeca) == 1 && (this.yPeca - yPeca) == 1) {
				boolean casaVazia = true;
				for (Peca p : pecaLista) {
					if (p.getxPeca() == xPeca && p.getyPeca() == yPeca) {
						casaVazia = false;
						break;
					}
				}

				if (casaVazia) {
					this.xPeca = xPeca;
					this.yPeca = yPeca;
					x = xPeca * 64;
					y = yPeca * 64;
				} else {
					resetarPosicao();
				}
			} else if (Math.abs(this.xPeca - xPeca) == 2 && Math.abs(this.yPeca - yPeca) == 2) {
				boolean casaVazia = true;
				int pecaComidaX = (this.xPeca + xPeca) / 2; // Coordenada X da peça a ser comida
				int pecaComidaY = (this.yPeca + yPeca) / 2; // Coordenada Y da peça a ser comida

				for (Peca p : pecaLista) {
					if (p.getxPeca() == pecaComidaX && p.getyPeca() == pecaComidaY) {
						if(p.isVermelho()) {
							p.removerPeca(); // Remove a peça que será comida
							p.setComeuPeca(true);
						} else {
							casaVazia = false;
						}
						break;
					}
				}

				if (casaVazia) {
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

	public abstract void mexerDama(int xPeca, int yPeca);

	public void resetarPosicao() {
		this.x = this.xPeca * 64;
		this.y = this.yPeca * 64;
	}

	public void removerPeca() {
		pecaLista.remove(this);
	}
}