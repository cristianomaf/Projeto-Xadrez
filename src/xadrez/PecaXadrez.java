package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public class PecaXadrez extends Peca {  // sub classe de Peca
	
	private Cor cor;
	//construtor

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro); // tabuleiro vem da super classe no caso Peca
		this.cor = cor;
	}
	
	//getters  da cor que nao tera set pois nao queremos editar a cor e um peca apenas acessa-la
	public Cor getCor() {
		return cor;
	}

	 
	
	

}
