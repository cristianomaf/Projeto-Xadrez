package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "K";
	}

	//metodo auxiliar possomover
	private boolean possoMover(Posicao posicao) {  // vai verificar se o rei pode mover para a posicao passada como parametro
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);  // pegamos a peca p que estiver na posicao que o rei quer mover
		return p== null || p.getCor() != getCor(); // retorna verdadeiro se peca que estiver na posicao que se quer mover  for nula ou for da cor diferente da do rei
	}
	
	
	@Override
	
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0,0); // criando variavel auxiliar posicao
		
		//teste das direcoes
		//acima
		p.setValores(posicao.getLinha()-1, posicao.getColuna()); // seta p uma posicao acima do rei
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) { // verifica se posicao p existe e se o rei pode mover para ela conforme o metodo criado acima 
			mat[p.getLinha()][p.getColuna()] = true; // marco a possicao da matriz de possiveis moviemntos do rei com true
		}
		//abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
		}
		
		//esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() -1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
		}
		
		//direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
		}
		//diagonais NW
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() -1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
		}
		//diagonal NE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() +1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
		}
		//diagonal SW
		p.setValores(posicao.getLinha() + 1, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
		}
		//diagonal SE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExiste(p)&& possoMover(p)) {
			mat[p.getLinha()][p.getColuna()]= true;
		}
		
		
		return mat;
	}

}
