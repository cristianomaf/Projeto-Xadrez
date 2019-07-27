package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}

	@Override
	public String toString() {
		return "B";
	}

	Posicao p = new Posicao(0, 0);

	@Override
	public boolean[][] possivelMovimentos() {
		Posicao p = new Posicao(0, 0); // cria uma posicao que vai servir para armazenar as posicoes possiveis de
										// moviementacao
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()]; // matriz com possiveis
																								// moviemtnos da peca
																								// apos verificacao

		// posicao noroeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1); // seta a posicao que quer verificar
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { // enquanto existir essa posicao e
																					// nao tiver nenhuma peca nela
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1); // atualiza a posicao p para proxima posicao diagonal
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// posicao nordeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { // enquanto existir essa posicao e
																					// nao tiver nenhuma peca nela
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1); // atualiza a posicao p para proxima posicao diagonal
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) { // existe posicao e tem uma peca oponente tambem
																		// pode mover
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// posicao sudeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { // enquanto existir essa posicao e
																					// nao tiver nenhuma peca nela
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1); // atualiza a posicao p para proxima posicao diagonal
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) { // existe posicao e tem uma peca oponente tambem
																		// pode mover
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// posicao sudoeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { // enquanto existir essa posicao e
																					// nao tiver nenhuma peca nela
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() -1); // atualiza a posicao p para proxima posicao diagonal
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) { // existe posicao e tem uma peca oponente tambem
																		// pode mover
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

}
