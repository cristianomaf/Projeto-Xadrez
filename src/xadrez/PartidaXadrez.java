package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	// Partida tem que ter um tabuleiro
	private Tabuleiro tabuleiro;

	// construtor padrao
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inicialConfig();
	}

	// metodo
	public PecaXadrez[][] getPecas() { // ele retorna as pecas do tipo PEcaXadrez e nao as pecas internas do tabuleiro
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		// percorrer a matriz pecas
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j); // downcasting para peca xadrez
			}
		}
		return mat;
	}

	private void colocaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocaPeca(peca, new XadrezPosicao(coluna, linha).paraPosicao()); // convertendo para matriz
	}

	private void inicialConfig() {
		colocaNovaPeca('b', 6, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		colocaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA));
	}

}
