package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0); // auxiliar

		// movimentos acima da peca que sao possiveis
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) { // enquanto existir posicao e nao
																					// tiver peca la
			mat[p.getLinha()][p.getColuna()] = true; // recebe valor verdadeiro que permitira movimentar a peca para
														// essa posicao
			p.setLinha(p.getLinha() - 1); // ando uma posicao acima e entao repete-se enquanto opcoes estiverem sendo
											// satisfeitas
		}
		// agora verifico se tem alguma peca adversaria na linha acima
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true; // se a posicao tiver uma pecaoponente tambem adicionamos posicao
														// como valida para mover
		}

		// movimentos possiveis para esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// para baixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

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
			p.setValores(p.getLinha() + 1, p.getColuna() - 1); // atualiza a posicao p para proxima posicao diagonal
		}
		if (getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) { // existe posicao e tem uma peca oponente tambem
																		// pode mover
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
