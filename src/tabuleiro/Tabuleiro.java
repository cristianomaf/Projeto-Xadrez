package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	// um tabuleiro tera varias pecas
	// entao utiliza-se matriz do tipo Peca
	private Peca[][] pecas;

	// contrutor
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroExcecao("Erro criar tabuleiro: precisa de ao menos 1 linha e coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas]; // instancia a matriz de pecas
	}

	// getters e setters das linhas e colunas somente
	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	// cria uma metodo retornando um objeto tipo Peca
	// o nome do metodo eh peca
	public Peca peca(int linha, int coluna) {
		if(!posicaoExiste(linha,coluna)) {
			throw new TabuleiroExcecao("Posicao nao está no tabuleiro");
		}
		return pecas[linha][coluna];
	}

	public Peca peca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroExcecao("Posicao nao está no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	// metodo colocar pecas
	public void colocaPeca(Peca peca, Posicao posicao) {
		if(temUmaPeca(posicao)) {
			throw new TabuleiroExcecao("Ja existe uma peca na posicao"+posicao);
		}
		// recebe uma peca e uma posicao
		// dentro da matriz na linha e coluna indicada recebe a peca
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		// peca criada recebe a posicao nova recebida como parametro do metodo
		peca.posicao = posicao;
	}

	// metodo auxiliar de teste
	private boolean posicaoExiste(int linha, int coluna) {
		// verifica se linhas passada no paremtro esta dentro da qtd de linha tabuleiro
		// e colunas
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;

	}
	// agora passa uma posicao e verifica se ela esta no limite do tabuleiro
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());

	}

	// verifica se tem uma peca nessa posicao
	public boolean temUmaPeca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroExcecao("Posicao nao esta no Tabuleiro");
		}
		return peca(posicao) != null;
	}

}
