package tabuleiro;

public class Posicao {
	private int linha;
	private int coluna;
	
	//construtor da classe	
	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	//getters e setters
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
	
	// sobrescrever o metodo to string para mostrar a posicao da peca na tela
	@Override
	public String toString() {
		return linha+ "," + coluna;
	}

	
}



