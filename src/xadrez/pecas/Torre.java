package xadrez.pecas;

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
	
}
