package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {  // sub classe de Peca
	
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
	
	//parte 17 
	//metodo que retorna posicao em posica de xadrez
	public XadrezPosicao getXadrezPosicao() {
		return XadrezPosicao.dePosicao(posicao); // pega uma posicao converte para pos. xadrez
	}
	

	protected boolean existePecaOponente(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao); // fazendo dowcasting para peca Xadrez
		return p != null && p.getCor() != cor;
	}
	
	

}
