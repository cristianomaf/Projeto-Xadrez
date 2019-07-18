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
	//aula13  - mostrando movimentos possiveis na tela
	public boolean [][] movimentosPossiveis(XadrezPosicao posicaoOrigem){
		Posicao posicao = posicaoOrigem.paraPosicao(); // converte para posicao de matriz
		validacaoPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possivelMovimentos();
	}
	
	
	
	// metodo movimento peca que recebe como parametros posicao origem e uma de
	// destino
	public PecaXadrez MovimentoPeca(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
		// converte posicao para posicao de matriz
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validacaoPosicaoOrigem(origem);
		validacaoPosicaoDestino(origem,destino);
		Peca pecaCapturada = FazerMovimento(origem, destino);
		return (PecaXadrez) pecaCapturada;
	}

	private Peca FazerMovimento(Posicao origem, Posicao destino) {

		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocaPeca(p, destino);
		return pecaCapturada;
	}

	private void validacaoPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) { // se nao existir peca nessa posicao
			throw new XadrezExcecao("N�o existe peca na posicao de origem");
		}
		// existe movimentos possiveis
		if (!tabuleiro.peca(posicao).ExisteMovimentoPossivel()) {
			throw new XadrezExcecao("Nao existe movimentos para essa peca");
		}
	}
	
	private void validacaoPosicaoDestino(Posicao origem,Posicao destino) {
		// para validar se possicao destino e valida
		if(!tabuleiro.peca(origem).possivelMovimentos(destino)) {
			// se para peca de origem a posicao de destino nao e um movimento possivel 
			throw new XadrezExcecao("N�o possivel movimentar essa peca para a posicao escolhida");
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
