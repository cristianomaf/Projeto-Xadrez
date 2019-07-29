package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
	
	private PartidaXadrez partida; // para associar o rei a partida para ter acessso as posicoes da partida e ser possivel verificar a jogda especial da troca com as torres

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partida) {
		super(tabuleiro, cor);
		this.partida =partida;
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
	
	private boolean testeJogadaTrocaTorre (Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		//teste se diferente de nulo se é uma torre se a cor dela e igual do rei e contador de moviemntos e igual a 0
		return p!=null && p instanceof Torre && p.getCor() == getCor() &&  p.getContadorMovimentos() == 0;
		
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
		
		//movimento especial de troca da torre pelo rei
		//
		if(getContadorMovimentos()==0 && !partida.getCheck()) {
			//movimento especial do lado do rei
			Posicao posT1 = new Posicao(posicao.getLinha(),posicao.getColuna()+3);
			if(testeJogadaTrocaTorre(posT1)) {
				//primeira casa direita do rei
				Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna()+1);
				Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna()+2);
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2)== null) {
					mat[posicao.getLinha()][posicao.getColuna()+2] = true;
				}
			}
		
			//movimento especial de troca da torre pelo rei lado rainha
			//
			//movimento especial do lado do rei
				Posicao posT2 = new Posicao(posicao.getLinha(),posicao.getColuna()-4);
				if(testeJogadaTrocaTorre(posT2)) {
					//primeira casa direita do rei
					Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna()-1);
					Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna()-2);
					Posicao p3 = new Posicao(posicao.getLinha(),posicao.getColuna()-3);
					if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2)== null && getTabuleiro().peca(p3)== null) {
						mat[posicao.getLinha()][posicao.getColuna()-2] = true;
					}
				}
		}
		
		
		return mat;
	}
	}

	
