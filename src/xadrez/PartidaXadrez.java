package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	//parte 15
	private int turno;
	private Cor jogadorAtual;		
	// Partida tem que ter um tabuleiro
	private Tabuleiro tabuleiro;
	
	//controle de pecas no tabuleiro e capturadas
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	

	// construtor padrao alterado com novas variaveis turno e jogador atual
	public PartidaXadrez() {
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		tabuleiro = new Tabuleiro(8, 8);
		inicialConfig();
	}
	//metodos get das novas variaveis
	public int getTurno() {
		return turno;
	}
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
		//inserido chamada do metodo para troca do turno
		proxTurno();
		
		return (PecaXadrez) pecaCapturada;
	}
	
	//parte 16 trocando peca da lista do tabuleiro para capturadas
	private Peca FazerMovimento(Posicao origem, Posicao destino) {

		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);		
		tabuleiro.colocaPeca(p, destino);
		
		if(pecaCapturada!=null) {
			pecasNoTabuleiro.remove(pecaCapturada); // remove do tabuleiro e adiciona nas capturadas
			pecasCapturadas.add(pecaCapturada);
			
		}
		return pecaCapturada;
		
	}

	private void validacaoPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) { // se nao existir peca nessa posicao
			throw new XadrezExcecao("Não existe peca na posicao de origem");
		}
		// existe movimentos possiveis
		// alteracao verifica se jogador atual pode mexer peca de sua cor 
		if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcecao("A peca escolhida nao e sua");
		}
		if (!tabuleiro.peca(posicao).ExisteMovimentoPossivel()) {
			throw new XadrezExcecao("Nao existe movimentos para essa peca");
		}
		
		
	}
	
	private void validacaoPosicaoDestino(Posicao origem,Posicao destino) {
		// para validar se possicao destino e valida
		if(!tabuleiro.peca(origem).possivelMovimentos(destino)) {
			// se para peca de origem a posicao de destino nao e um movimento possivel 
			throw new XadrezExcecao("Não possivel movimentar essa peca para a posicao escolhida");
		}
	}
	
	//metodo proximo turno
	private void proxTurno() {
		turno++; // turno é incrementado
		//verifica se a cor atual for branca troca para preto caso contario fica branca
		jogadorAtual = (jogadorAtual == Cor.BRANCA)? Cor.PRETO : Cor.BRANCA; 
	}

	//parte 16 acrescentando no metodo as novas pecas na lista de pecas do tabuleiro
	
	private void colocaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocaPeca(peca, new XadrezPosicao(coluna, linha).paraPosicao()); // convertendo para matriz
		pecasNoTabuleiro.add(peca);//adiciona pecas na lista de pecas do tabuleiro
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
