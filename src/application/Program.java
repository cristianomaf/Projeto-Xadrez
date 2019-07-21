package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezExcecao;
import xadrez.XadrezPosicao;

public class Program {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		// instanciar partida de xadrez
		PartidaXadrez partida = new PartidaXadrez();

		while (true) { // repete indefinidamente
			// imprimir partida
			// antes de imprimir vamos rodar o metodo limpa tela
	try {
			Interface.limpaTela();

			Interface.imprimePartida(partida);
			System.out.println();
			System.out.println("Posicao Origem :");
			XadrezPosicao origem = Interface.lerXadrezPosicao(scan);
			
			//aula13
			//cria variavel matriz que recebe movimentos possiveis
			boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
			Interface.limpaTela();
			Interface.impressaoTabuleiro(partida.getPecas(),movimentosPossiveis);
						
			System.out.println();
			System.out.println("Posicao de Destino");
			XadrezPosicao destino = Interface.lerXadrezPosicao(scan);

			PecaXadrez pecaCapturada = partida.MovimentoPeca(origem, destino);
	}
	catch(XadrezExcecao e) { // tratando excecao
		System.out.println(e.getMessage());
		scan.nextLine();  // aguarda um tecla enter
	}
	catch(InputMismatchException e) {
		System.out.println(e.getMessage());
		scan.nextLine();
	}
	
	
		}

	}

}
