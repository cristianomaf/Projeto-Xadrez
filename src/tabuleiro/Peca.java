package tabuleiro;

public abstract class Peca {

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

	public abstract boolean[][] possivelMovimentos();
	
	public boolean possivelMovimentos(Posicao posicao) {
		return possivelMovimentos()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean ExisteMovimentoPossivel() {
		boolean[][] mataux = possivelMovimentos();
		for(int i = 0; i<mataux.length; i++) {
			for(int j=0; j<mataux.length; j++) {
				if (mataux [i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	

}