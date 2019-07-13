package tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	
	//um tabuleiro tera varias pecas
	// entao utiliza-se matriz do tipo Peca
	private Peca[][] pecas;
	
	// contrutor 
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas]; // instancia a matriz de pecas 
	}

	//getters e setters das linhas e colunas somente
	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	
}
