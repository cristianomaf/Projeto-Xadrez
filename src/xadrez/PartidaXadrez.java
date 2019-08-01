package xadrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
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
	private PecaXadrez enPassantVulneravel; // inicia nula
	private PecaXadrez promocao;

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
	public PecaXadrez getEnpassantVulneravel() {
		return enPassantVulneravel;
	}
	public PecaXadrez getPromocao() {
		return promocao;
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
		
		PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino);
		
		// movimento especial de promocao do peao
		promocao = null;
		//se a peca movida for uma innstacia de peao
		if(pecaMovida instanceof Peao) {
			//verifica se o peao chegou ao final tanto branco quanto preto
			if(pecaMovida.getCor() == Cor.BRANCA && destino.getLinha() ==0 ||pecaMovida.getCor() == Cor.PRETO && destino.getLinha() == 7  ) {
				promocao = (PecaXadrez)tabuleiro.peca(destino);
			promocao = trocaPecaPromovida("Q"); // padrao sera troca pela rainha inicialmente
			}
		}
		
		
		// verificar se o oponente ficou em check apos a jogada
		check = (testCheck(oponente(jogadorAtual))) ? true : false;
		// test check recebe uma cor como parametro
		// oponente metodo que recebe cor
		// jogador atual e uma cor

		// verificando se jogada deixou em chek mate

		if (testCheckMate(oponente(jogadorAtual))) {
			checkMate = true; // se nao acabar eu passo para o prosimo turno
		} else {
			// inserido chamada do metodo para troca do turno
			proxTurno();
		}
		
		//movimento especial en passant peao que fica vulneravel quando inicia com dois movimentos para frente
		if(pecaMovida instanceof Peao &&(destino.getLinha()== origem.getLinha()-2 || destino.getLinha() == origem.getLinha()+2 )) {
			enPassantVulneravel = pecaMovida;
		}
		else {
			enPassantVulneravel = null;
		}
		
		
		return (PecaXadrez) pecaCapturada;
	}
	
	//parte final
	public PecaXadrez trocaPecaPromovida(String tipo) {
		//se a peca promovida for null
		if(promocao == null) {
			throw new IllegalStateException("Nao houve peca promovida");			
		} 
		//verifica se a letra recebida como parametro representa uma peca valida
		//equals para comparar o tipo classe string verifica se string B é igual a string B
		if(!tipo.equals("B")&& !tipo.equals("R") && !tipo.equals("C") && !tipo.equals("Q")) {
			throw new InvalidParameterException("Tipo invalido para promocao");
		}
		
		Posicao pos = promocao.getXadrezPosicao().paraPosicao();
		Peca p = tabuleiro.removerPeca(pos);
		pecasNoTabuleiro.remove(p);
		
		//instancia uma nova peca para substituir o peao
		
		PecaXadrez novaPeca = novaPeca(tipo,promocao.getCor());
		tabuleiro.colocaPeca(novaPeca, pos);
		pecasNoTabuleiro.add(novaPeca);
		
		return novaPeca;
	}
	//metodo auxiliar instancia peca especifica para troca pelo peao
	private PecaXadrez novaPeca(String tipo, Cor cor) {
		if(tipo.equals("B")) return new Bispo(tabuleiro,cor);
		if(tipo.equals("C")) return new Cavalo(tabuleiro,cor);
		if(tipo.equals("Q")) return new Rainha(tabuleiro,cor);
		return new Torre(tabuleiro,cor);
	}

	// parte 16 trocando peca da lista do tabuleiro para capturadas
	private Peca FazerMovimento(Posicao origem, Posicao destino) {

		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
		p.incrementoContadorMovimentos(); // incremento um movimento realizado
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocaPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada); // remove do tabuleiro e adiciona nas capturadas
			pecasCapturadas.add(pecaCapturada);

		}
		// moviemto especial do rei pela torre
		// verifica se p e uma instancia de rei e destino = origem mais dois nas coluna
		// ou seja andou duas casas a direita
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.colocaPeca(torre, destinoTorre);
			torre.incrementoContadorMovimentos();
		}

		// moviemto especial do rei pela torre
		// verifica se p e uma instancia de rei e destino = origem mais dois nas coluna
		// ou seja andou duas casas a direita
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			// retira torre da posicao origem
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
			// coloca no destino
			tabuleiro.colocaPeca(torre, destinoTorre);
			// incrementa movimentos da torre
			torre.incrementoContadorMovimentos();
		}
		
		//enpassant movimento peao
		if(p instanceof Peao) {
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == null){
				Posicao posicaoPeao;
				if(p.getCor() == Cor.BRANCA) {
					posicaoPeao = new Posicao(destino.getLinha()+1, destino.getColuna());
				}else {
					posicaoPeao = new Posicao(destino.getLinha()-1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
				
			}
		}
		

		return pecaCapturada;

	}

	// parte 17 metodo para desfazer movimento
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		// cria uma peca p que recebe peca do destino
		// parte 19 so podemos chamar o metodo que decrementa sas jogadas de uma
		// PecaXadrez
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
		p.decrementoContadorMovimentos();
		// pega peca p e coloca denovo na origem
		tabuleiro.colocaPeca(p, origem);

		if (pecaCapturada != null) { // se alguma peca foi capturada
			tabuleiro.colocaPeca(pecaCapturada, destino); // coloca peca no destino novamente
			pecasCapturadas.remove(pecaCapturada); // remove a peca da lista capturadas
			pecasNoTabuleiro.add(pecaCapturada); // adicona na pecas no tabuleiro.
		}
		//desfazendo moviemento especial
		// moviemto especial do rei pela torre
		// verifica se p e uma instancia de rei e destino = origem mais dois nas coluna
		// ou seja andou duas casas a direita
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.colocaPeca(torre, origemTorre);
			torre.decrementoContadorMovimentos();
		}

		// moviemto especial do rei pela torre
		// verifica se p e uma instancia de rei e destino = origem mais dois nas coluna
		// ou seja andou duas casas a direita
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			// retira torre da posicao destino
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
			// coloca na origem novamente
			tabuleiro.colocaPeca(torre, origemTorre);
			// decrementa movimentos da torre
			torre.decrementoContadorMovimentos();
		}
	
		//enpassant
		if(p instanceof Peao) {
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulneravel){
				PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(destino);
				Posicao posicaoPeao;
				if(p.getCor() == Cor.BRANCA) {
					posicaoPeao = new Posicao(3, destino.getColuna());
				}else {
					posicaoPeao = new Posicao(4, destino.getColuna());
				}
				tabuleiro.colocaPeca(peao, posicaoPeao);
				
			}
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
		colocaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA, this));
		colocaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA,this));
		colocaNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA,this));
		colocaNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA,this));
		colocaNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA,this));
		colocaNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA,this));
		colocaNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA,this));
		colocaNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA,this));
		colocaNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA,this));

		colocaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this)); // this e a partida que no rei tem que ser passada
																		// como parametro
		colocaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocaNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO,this));
		colocaNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO,this));
		colocaNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO,this));
		colocaNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO,this));
		colocaNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO,this));
		colocaNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO,this));
		colocaNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO,this));
		colocaNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO,this));

	}

}
