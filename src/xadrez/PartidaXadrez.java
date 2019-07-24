package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	// parte 15
	private int turno;
	private Cor jogadorAtual;
	// Partida tem que ter um tabuleiro
	private Tabuleiro tabuleiro;
	// parte 17
	private boolean check; // propriedade check do xadrez
	// parte 18
	private boolean checkMate;

	// controle de pecas no tabuleiro e capturadas
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	// construtor padrao alterado com novas variaveis turno e jogador atual
	public PartidaXadrez() {
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		tabuleiro = new Tabuleiro(8, 8);
		check = false; // isso nao e necessario atribiur pois normalemente ela ja comeca com false
		inicialConfig();
	}

	// metodos get das novas variaveis
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
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

	// aula13 - mostrando movimentos possiveis na tela
	public boolean[][] movimentosPossiveis(XadrezPosicao posicaoOrigem) {
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
		validacaoPosicaoDestino(origem, destino);
		Peca pecaCapturada = FazerMovimento(origem, destino);
		// parte 17 depois de executar movimento testar se o jogador nao esta se
		// coloando em check
		if (testCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada); // desfaz o movimento e adiciona excecao
			throw new XadrezExcecao("Voce nao pode se colocar em check");

		}
		// verificar se o oponente ficou em check apos a jogada
		check = (testCheck(oponente(jogadorAtual))) ? true : false;
		// test check recebe uma cor como parametro
		// oponente metodo que recebe cor
		// jogador atual e uma cor
		
		//verificando se jogada deixou em chek mate
		
		if(testCheckMate(oponente(jogadorAtual))) {
			checkMate = true; // se nao acabar eu passo para o prosimo turno
		}else {
		// inserido chamada do metodo para troca do turno
		proxTurno();
		}

		return (PecaXadrez) pecaCapturada;
	}

	// parte 16 trocando peca da lista do tabuleiro para capturadas
	private Peca FazerMovimento(Posicao origem, Posicao destino) {

		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocaPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada); // remove do tabuleiro e adiciona nas capturadas
			pecasCapturadas.add(pecaCapturada);

		}
		return pecaCapturada;

	}

	// parte 17 metodo para desfazer movimento
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		// cria uma peca p que recebe peca do destino
		Peca p = tabuleiro.removerPeca(destino);
		// pega peca p e coloca denovo na origem
		tabuleiro.colocaPeca(p, origem);

		if (pecaCapturada != null) { // se alguma peca foi capturada
			tabuleiro.colocaPeca(pecaCapturada, destino); // coloca peca no destino novamente
			pecasCapturadas.remove(pecaCapturada); // remove a peca da lista capturadas
			pecasNoTabuleiro.add(pecaCapturada); // adicona na pecas no tabuleiro.
		}

	}

	private void validacaoPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) { // se nao existir peca nessa posicao
			throw new XadrezExcecao("Não existe peca na posicao de origem");
		}
		// existe movimentos possiveis
		// alteracao verifica se jogador atual pode mexer peca de sua cor
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcecao("A peca escolhida nao e sua");
		}
		if (!tabuleiro.peca(posicao).ExisteMovimentoPossivel()) {
			throw new XadrezExcecao("Nao existe movimentos para essa peca");
		}

	}

	private void validacaoPosicaoDestino(Posicao origem, Posicao destino) {
		// para validar se possicao destino e valida
		if (!tabuleiro.peca(origem).possivelMovimentos(destino)) {
			// se para peca de origem a posicao de destino nao e um movimento possivel
			throw new XadrezExcecao("Não possivel movimentar essa peca para a posicao escolhida");
		}
	}

	// metodo proximo turno
	private void proxTurno() {
		turno++; // turno é incrementado
		// verifica se a cor atual for branca troca para preto caso contario fica branca
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETO : Cor.BRANCA;
	}

	// parte 17 metodo oponente e localiza Rei
	private Cor oponente(Cor cor) { // vai retornar a cor do oponente
		return (cor == Cor.BRANCA) ? Cor.PRETO : Cor.BRANCA; // se cor for branca retorno preto caso contrario retorno
																// preto
	}

	private PecaXadrez rei(Cor cor) { // recebe cor como argumento
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		// filtro de lista //peca x tal que
		for (Peca p : list) { // para cada peca p da lista list
			if (p instanceof Rei) { // se peca p for uma instancia de rei
				return (PecaXadrez) p; // retorna ela como pecaXadrez
			} // e possivel que nao se encontre um rei pelo menos na logica de programa
		}
		throw new IllegalStateException("Nao existe rei" + cor + " no tabuleiro"); // isso nao deve ocorrer pois caso
																					// ocorra a logica do programa
																					// estara ruim

	}

	private boolean testCheck(Cor cor) {
		// posicao rei e converte para matriz
		Posicao posicaoRei = rei(cor).getXadrezPosicao().paraPosicao(); // metodo oponente devolve a cor do oponente
		List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecasDoOponente) { // para cada peca p na lista dde pecas do oponente
			boolean[][] mat = p.possivelMovimentos(); // coloca em uma matriz os possiveis movimentos da peca adversaria
														// p
			// verificar se na matriz de posicoes possiveis de cada peca adversaria a
			// posicao do rei esta entre as posicoes que a peca adversaria pode mover
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false; // retorna falsa se nenhuma peca adversaria esta com possibilidade de chegar ao
						// Rei
	}

	// parte 18
	// metodo que vai testar o check mate
	private boolean testCheckMate(Cor cor) {
		if (!testCheck(cor)) { // se nao estiver em check tambem nao estara em checkMate
			return false;
		}
		// cria uma lista com todas as pecas dessa co
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		// percorre a lista
		for (Peca p : list) {
			// primeiro capturar movimentos possiveis de cada peca p
			boolean[][] mat = p.possivelMovimentos();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) { // para cada elemento da matriz
					if (mat[i][j]) { // esse movimento possivel tira o rei do check?
						// movemos essa peca para posicao possivel
						Posicao origem = ((PecaXadrez) p).getXadrezPosicao().paraPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = FazerMovimento(origem, destino);
						// testa o movimento se tirou o jogo do check
						boolean testCheck = testCheck(cor);
						// desfaz o movimento
						desfazerMovimento(origem, destino, pecaCapturada);
						// se o test check deu falso ou seja nao estava em chek
						if (!testCheck) {
							return false;
						}

					}
				}
			}

		}
		return true;
	}

	// parte 16 acrescentando no metodo as novas pecas na lista de pecas do
	// tabuleiro

	private void colocaNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocaPeca(peca, new XadrezPosicao(coluna, linha).paraPosicao()); // convertendo para matriz
		pecasNoTabuleiro.add(peca);// adiciona pecas na lista de pecas do tabuleiro
	}

	private void inicialConfig() {
		colocaNovaPeca('h', 7, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA));
		
		colocaNovaPeca('b', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaNovaPeca('a', 8, new Rei(tabuleiro, Cor.PRETO));
	}

}
