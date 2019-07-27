package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	@Override
	public String toString() {
		return "C";
	}
	
	//metodo auxiliar que verifica se pode mover 
	private boolean possoMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	

	@Override
	public boolean[][] possivelMovimentos() {
		
		//matriz movimentos possiveis com tamanho igual a do tabuleiro
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		//auxiliar posicao
		Posicao p = new Posicao(0,0);
	
		//possiveis moviemntos do cavalo
		p.setValores(posicao.getLinha()-1, posicao.getColuna()-2);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;			
		}
		
		p.setValores(posicao.getLinha()-2, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;			
		}
		
		p.setValores(posicao.getLinha()-2, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;			
		}
		
		p.setValores(posicao.getLinha()-1, posicao.getColuna()+2);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;			
		}
		
		p.setValores(posicao.getLinha()+1, posicao.getColuna()+2);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;			
		}
		p.setValores(posicao.getLinha()+2, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;			
		}
		
		p.setValores(posicao.getLinha()+2, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;			
		}
		p.setValores(posicao.getLinha()+1, posicao.getColuna()-2);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;			
		}
		
		
		
		return mat;
	}
	

}
