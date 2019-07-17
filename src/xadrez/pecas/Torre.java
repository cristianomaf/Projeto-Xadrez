package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{ // classe Torre sera uma extensao de PecaXadrez

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);  // heranca da superclasse Pecaxadrez	
	}

	
	
	//o metodo toString iremos sobrescrever para retornar a letra T que representara a Torre
	@Override
	public String toString() {
		return "R";
	}



	@Override
	//implementacao basica torre colocando retornar uma matriz de elementos falsos o que vai fazer com que a torre nao possa se mover por enquanto
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao (0,0); //auxiliar
		
		//movimentos acima da peca que sao possiveis		
		p.setValores(posicao.getLinha()-1,posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) { // enquanto existir posicao e nao tiver peca la
			mat[p.getLinha()][p.getColuna()]= true; // recebe valor verdadeiro que permitira movimentar a peca para essa posicao
			p.setLinha(p.getLinha()-1); //ando uma posicao acima e entao repete-se enquanto opcoes estiverem sendo satisfeitas
		}
		//agora verifico se tem alguma peca adversaria na linha acima
		if(getTabuleiro().posicaoExiste(p)&& existePecaOponente(p) ) {
			mat[p.getLinha()][p.getColuna()]= true; //se a posicao tiver uma pecaoponente tambem adicionamos posicao como valida para mover
		}
		
		//movimentos possiveis para esquerda
		p.setValores(posicao.getLinha(),posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
			p.setColuna(p.getColuna()-1);
		}
		if(getTabuleiro().posicaoExiste(p)&& existePecaOponente(p) ) {
			mat[p.getLinha()][p.getColuna()]= true; 
		}
		
		// direita 
		p.setValores(posicao.getLinha(),posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
			p.setColuna(p.getColuna()+1);
		}
		if(getTabuleiro().posicaoExiste(p)&& existePecaOponente(p) ) {
			mat[p.getLinha()][p.getColuna()]= true; 
		}
		//para baixo
		p.setValores(posicao.getLinha()+1,posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
			p.setLinha(p.getLinha()+1);
		}
		if(getTabuleiro().posicaoExiste(p)&& existePecaOponente(p) ) {
			mat[p.getLinha()][p.getColuna()]= true; 
		}
		return mat;
	}
	
}
