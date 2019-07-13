package tabuleiro;

public class Tabuleiro {
	
	private int linha;
	private int coluna;	
	//um tabuleiro tera varias pecas
	// entao utiliza-se matriz do tipo Peca
	private Peca[][] pecas;
	
	// contrutor 
	public Tabuleiro(int linha, int coluna) {
		this.linha = linha;
		this.coluna= coluna;
		pecas = new Peca[linha][coluna]; // instancia a matriz de pecas 
	}

	//getters e setters das linhas e colunas somente
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	// cria uma metodo retornando um objeto tipo Peca 
	// o nome do metodo eh peca
	public Peca peca(int linha, int coluna) {
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
}
