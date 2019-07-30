package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{
	
	//dependencia para partida
	private PartidaXadrez partida;

	public Peao(Tabuleiro tabuleiro, Cor cor,PartidaXadrez partida) {
		super(tabuleiro, cor);
		this.partida = partida;
		
	}

	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		if(getCor() == Cor.BRANCA) { // testa se cor do peao é branca
			// se posicao 1 linha acima e na mesma coluna 
			p.setValores(posicao.getLinha()-1, posicao.getColuna());
			// se posicao existe e estiver vazia posicao na matriz vai para true ou seja é possivel mover o peao para ela
			if(getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//outra regra do peao é que na primeira jogada da peca ele pode mover ate duas casas
			p.setValores(posicao.getLinha()-2, posicao.getColuna());
			//precisamos de outra posicao para usar pois peao tem que verificar duas posicoes possiveis que ele pode mover na primeira jogada
			Posicao p2 = new Posicao(posicao.getLinha() -1,posicao.getColuna()); 
			
			if(getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2)&& !getTabuleiro().temUmaPeca(p2) && getContadorMovimentos()==0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//teste se tem pecas adversaria na diagonal ele pode mover para lá
			p.setValores(posicao.getLinha()-1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha()-1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			//especial movimento enPassant
			//esta na linha 3
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(),posicao.getColuna()-1);
				if(getTabuleiro().posicaoExiste(esquerda) && existePecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partida.getEnpassantVulneravel()) {
					mat[esquerda.getLinha() -1 ] [esquerda.getColuna() ] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(),posicao.getColuna()+1);
				if(getTabuleiro().posicaoExiste(direita) && existePecaOponente(direita) && getTabuleiro().peca(direita) == partida.getEnpassantVulneravel()) {
					mat[direita.getLinha() -1 ] [direita.getColuna() ] = true;
				}
				
			}
		}
		else {
			p.setValores(posicao.getLinha()+1, posicao.getColuna());
			// se posicao existe e estiver vazia posicao na matriz vai para true ou seja é possivel mover o peao para ela
			if(getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//outra regra do peao é que na primeira jogada da peca ele pode mover ate duas casas
			p.setValores(posicao.getLinha()+2, posicao.getColuna());
			//precisamos de outra posicao para usar pois peao tem que verificar duas posicoes possiveis que ele pode mover na primeira jogada
			Posicao p2 = new Posicao(posicao.getLinha() + 1,posicao.getColuna()); 
			
			if(getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2)&& !getTabuleiro().temUmaPeca(p2)&& getContadorMovimentos()==0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//teste se tem pecas adversaria na diagonal ele pode mover para lá
			p.setValores(posicao.getLinha()+1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValores(posicao.getLinha()+1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExiste(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//especial movimento enPassant
			//esta na linha 3
			if(posicao.getLinha() == 4) { // linha que peao preto pode passar pelo en passant
				Posicao esquerda = new Posicao(posicao.getLinha(),posicao.getColuna()-1);
				if(getTabuleiro().posicaoExiste(esquerda) && existePecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partida.getEnpassantVulneravel()) {
					mat[esquerda.getLinha() +1 ] [esquerda.getColuna() ] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(),posicao.getColuna()+1);
				if(getTabuleiro().posicaoExiste(direita) && existePecaOponente(direita) && getTabuleiro().peca(direita) == partida.getEnpassantVulneravel()) {
					mat[direita.getLinha() +1 ] [direita.getColuna() ] = true;
				}
				
			}
		}
		
		return mat;
	}

	@Override 
	public String toString() {
		return "P";
		
	}
}
