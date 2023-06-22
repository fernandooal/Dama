package br.com.unicuritiba.dama.model;

import java.util.LinkedList;

//conceito de classe abstrata, o que impossibilita de criar instâncias dessa classe, mas libera para fazer isso a partir das classes filhas,
//o que permite utilizar o metodo de mexer dama da classe filha no tabuleiro.
public abstract class Peca {
	// atributos necessarios
	protected int xPeca;
	protected int yPeca;
	protected int x;
	protected int y;
	protected boolean vermelho;
	public static LinkedList<Peca> pecaLista;
	protected boolean dama;
	private boolean comeuPeca;

	// construtor
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

	// getters and setters
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

	// método para movimentar a peça
	public final void mexerPeca(int xPeca, int yPeca) {
		// verificando a cor pois, diferente da dama, a peça normal não pode voltar,
		// então o y para as pretas e para as vermelhas se difere entre positivo e
		// negativo
		if (isVermelho() == true) {
			// verificando se a peça se movimentou horizontalmente para qualquer lado e
			// verticalmente para baixo (para cima a peça vermelha não pode)
			if (Math.abs(this.xPeca - xPeca) == 1 && (this.yPeca - yPeca) == -1) {
				boolean casaVazia = true;
				// passando pela lista inteira de peças para verificar se não existe nenhuma
				// outra ocupando a posição
				for (Peca p : pecaLista) {
					if (p.getxPeca() == xPeca && p.getyPeca() == yPeca) {
						casaVazia = false;
						break;
					}
				}

				// se a casa estiver vazia, a peça conseguirá se movimentar
				if (casaVazia) {
					this.xPeca = xPeca;
					this.yPeca = yPeca;
					x = xPeca * 64;
					y = yPeca * 64;
				} else {
					// caso contrário, resetará a posição
					resetarPosicao();
				}
				// verificando a mesma coisa do if passado, mas andando 2 casas no x e no y para
				// comer uma peça
			} else if (Math.abs(this.xPeca - xPeca) == 2 && Math.abs(this.yPeca - yPeca) == 2) {
				boolean casaVazia = true;
				// coordenada x e y da peça a ser comida
				int pecaComidaX = (this.xPeca + xPeca) / 2;
				int pecaComidaY = (this.yPeca + yPeca) / 2;

				// passando pela lista verificando se tem alguma peça nas coordenadas que não
				// seja vermelha
				for (Peca p : pecaLista) {
					if (p.getxPeca() == pecaComidaX && p.getyPeca() == pecaComidaY) {
						if (p.isVermelho() == false) {
							p.removerPeca();
							p.setComeuPeca(true);
							;
						} else {
							casaVazia = false;
						}
						break;
					}
				}

				// se a casa estiver vazia, a peça se movimentará
				if (casaVazia) {
					this.xPeca = xPeca;
					this.yPeca = yPeca;
					x = xPeca * 64;
					y = yPeca * 64;
				} else {
					// caso contrário, reseta a posição
					resetarPosicao();
				}
			} else {
				// se a distância movimentada for incompatível com as regras do jogo, resetará a
				// posição
				resetarPosicao();
			}
		} else {
			// mesmo processo para a cor preta
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
						if (p.isVermelho()) {
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

	// criando um metodo abstrato para poder utilizar da classe filha
	public abstract void mexerDama(int xPeca, int yPeca);

	// método de resetar a posição caso o movimento seja inválido
	public void resetarPosicao() {
		this.x = this.xPeca * 64;
		this.y = this.yPeca * 64;
	}

	// método de remover a peça da lista e consequentemente do jogo
	public void removerPeca() {
		pecaLista.remove(this);
	}
}
