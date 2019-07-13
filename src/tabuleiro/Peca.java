package tabuleiro;

public class Peca {

	protected Posicao posicao;
	private Tabuleiro tabuleiro; // vinculando peca a um tabuleiro
	
	//construtor
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null; // que por padrao ja seria vazio caso nao fosse declarada aqui
	}

	//getter e setter 
	// teremos apenas o get do tabuleiro e ele sera protected
	// apenas classes do mesmo pacote e sub classe que podem acessa-lo
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	
	
	
	

}