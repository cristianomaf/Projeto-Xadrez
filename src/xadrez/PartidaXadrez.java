package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
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
	
	//metodo movimento peca que recebe como parametros posicao origem e uma de destino
	public PecaXadrez MovimentoPeca(XadrezPosicao posicaoOrigem,XadrezPosicao posicaoDestino) {
		//converte posicao para posicao de matriz
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validacaoPosicaoOrigem(origem);
		Peca pecaCapturada = FazerMovimento(origem,destino);
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca FazerMovimento(Posicao origem, Posicao destino) {
		
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocaPeca(p, destino);
		return pecaCapturada;
	}
	
	
	private void validacaoPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.temUmaPeca(posicao)) { // se nao existir peca nessa posicao
			throw new XadrezExcecao("Não existe peca na posicao de origem");
		}
	}
	
	
	private void colocaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocaPeca(peca, new XadrezPosicao(coluna, linha).paraPosicao()); // convertendo para matriz
	}

	private void inicialConfig() {
		colocaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));
	
		colocaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		colocaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		colocaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		colocaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}

}
