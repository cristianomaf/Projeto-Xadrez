package xadrez;

import tabuleiro.Posicao;

public class XadrezPosicao {
	private char coluna;
	private int linha;

	public XadrezPosicao(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha >8) {
			throw new XadrezExcecao("Erro ao instanciar XadrezPosicao. Valores validos sao de a1 a h8 ");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	//metodos get apenas
	public char getColuna() {
		return coluna;
	}
	
	public int getLinha() {
		return linha;
	}
	
	//converte posicao real do xadrez para a da matriz
	protected Posicao paraPosicao() {
		return new Posicao(8 - linha ,coluna -'a');
		
	}
	//dada posicao na matriz converte para posicao de xadrez
	protected static XadrezPosicao dePosicao(Posicao posicao) {
	return new XadrezPosicao((char)('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}
		
	@Override
	public String toString() {
		return ""+coluna+linha; // aspas forcam o compilador a concatenar strings
	}
	
	
	
	
}
