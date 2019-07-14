package application;

import xadrez.PecaXadrez;

public class Interface {
	// metodo static pois eh da classe apenas
	public static void ImpressaoTabuleiro (PecaXadrez[][] pecas) { // recebe uma matriz de pecasXadrez
		for (int i =0; i< pecas.length; i++) {
			System.out.print(8 - i + "  ");
			for(int j=0 ; j< pecas.length; j++) {
				imprimePeca(pecas[i][j]);
				System.out.print("  ");
			}
			System.out.println();
		}
		System.out.println("   a   b   c   d   e   f   g   h");
				
	}
	 //metodo auxiliar imprime uma peca
	// recebe uma peca 
		private static void imprimePeca(PecaXadrez peca) { 
			if(peca == null) { // se peca estiver vazia
				System.out.print("-");
			}else {// caso exista peca imprime ela
				System.out.print(peca);
			}
			System.out.print(" ");
		}
}

	