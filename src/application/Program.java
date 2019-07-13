package application;

import xadrez.PartidaXadrez;


public class Program {

	public static void main(String[] args) {
		
		//instanciar partida de xadrez
		PartidaXadrez  partida = new PartidaXadrez();
		
		//imprimir partida
		Interface.ImpressaoTabuleiro(partida.getPecas());
	}

}
