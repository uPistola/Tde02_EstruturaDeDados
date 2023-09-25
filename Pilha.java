package Tde02;

import java.util.Random;
import java.util.Scanner;

class NoDaPilha {
	int dado;
	NoDaPilha proximo;

	public NoDaPilha(int dado) {
		this.dado = dado;
		this.proximo = null;
	}
}

public class Pilha {
	private NoDaPilha topo;
	private int movimentosExecutados;
	private String nome;

	public Pilha(String nome) {
		this.topo = null;
		this.movimentosExecutados = 0;
		this.nome = nome;
	}

	public boolean estaVazia() {
		return topo == null;
	}

	public void push(int dado) { // Changed from empilhar to push
		NoDaPilha novoNo = new NoDaPilha(dado);
		novoNo.proximo = topo;
		topo = novoNo;
	}

	public int pop() { // Changed from desempilhar to pop
		if (estaVazia()) {
			System.out.println("A pilha esta vazia. Nao e possivel remover elementos.");
			return -1; // Valor de erro
		}
		int valorRemovido = topo.dado;
		topo = topo.proximo;
		return valorRemovido;
	}

	public void imprimir() {
		if (estaVazia()) {
			System.out.println("A pilha esta vazia.");
			return;
		}
		System.out.print("Elementos da pilha: ");
		NoDaPilha atual = topo;
		while (atual != null) {
			System.out.print(atual.dado + " ");
			atual = atual.proximo;
		}
		System.out.println();
	}

	public int getMovimentosExecutados() {
		return movimentosExecutados;
	}

	public void incrementarMovimentosExecutados() {
		movimentosExecutados++;
	}

	public boolean vazia() {
		return topo == null;
	}

	public int tamanho() {
		int tamanho = 0;
		NoDaPilha atual = topo;

		while (atual != null) {
			tamanho++;
			atual = atual.proximo;
		}

		return tamanho;
	}

	public String getNome() {
		return nome;
	}

	public static void ordenarPilhaCrescenteComPassos(Pilha origem, Pilha destino, Pilha auxiliar) {
		int tamanho = origem.tamanho();
		int movimentos = 0;

		for (int i = 0; i < tamanho; i++) {
			int menor = Integer.MAX_VALUE;

			while (!origem.vazia()) {
				int valor = origem.pop();
				if (valor < menor) {
					if (menor != Integer.MAX_VALUE) {
						auxiliar.push(menor);
					}
					menor = valor;
				} else {
					auxiliar.push(valor);
				}
				movimentos++;
			}

			destino.push(menor);

			System.out.println("Movimento " + (i + 1) + ": Mover disco " + menor + " da " + origem.getNome()
					+ " para a " + destino.getNome());

			while (!auxiliar.vazia()) {
				origem.push(auxiliar.pop());
			}
		}

		System.out.println("Numero total de movimentos: " + movimentos);
	}

	public static void ordenarPilhaDecrescenteComPassos(Pilha origem, Pilha destino, Pilha auxiliar) {
		int tamanho = origem.tamanho();
		int movimentos = 0;

		for (int i = 0; i < tamanho; i++) {
			int maior = Integer.MIN_VALUE; // Alterado para encontrar o maior valor

			while (!origem.vazia()) {
				int valor = origem.pop();
				if (valor > maior) { // Alterado para encontrar o maior valor
					if (maior != Integer.MIN_VALUE) {
						auxiliar.push(maior); // Alterado para empilhar o maior valor
					}
					maior = valor; // Alterado para atualizar o maior valor
				} else {
					auxiliar.push(valor);
				}
				movimentos++;
			}

			destino.push(maior);

			System.out.println("Movimento " + (i + 1) + ": Mover disco " + maior + " da " + origem.getNome()
					+ " para a " + destino.getNome());

			while (!auxiliar.vazia()) {
				origem.push(auxiliar.pop());
			}
		}

		System.out.println("Número total de movimentos: " + movimentos);
	}

	public int verificar(int tipo) {
		NoDaPilha atual = topo;
		int ultimo = 0;
		switch (tipo) {
		case 2:
			if (topo == null) {
				return 1;
			}

			while (atual != null) {
				if (atual.dado < ultimo) {
					return 0;
				} // Se encontrar um valor fora de ordem, retorna 0
				ultimo = atual.dado;
				atual = atual.proximo;
			}

			return 3; // A pilha está em ordem crescente
		case 1:
			ultimo = 100;
			if (topo == null) {
				return 1;
			}

			while (atual != null) {
				if (atual.dado > ultimo) {
					return 0;
				} // Se encontrar um valor fora de ordem, retorna 0
				ultimo = atual.dado;
				atual = atual.proximo;
			}

			return 3; // A pilha está em ordem crescente
		}
		return 0;
	}

	public static void main(String[] args) {
		Pilha pilha1 = new Pilha("Pilha 1");
		Pilha pilha2 = new Pilha("Pilha 2");
		Pilha pilha3 = new Pilha("Pilha 3");

		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o tamanho das pilhas: ");
		int tamanhoPilhas = scanner.nextInt();
		System.out.println(" --------------------------------------------");
		System.out.println("Qual ordem voce deseja: \n(1)Crescente \n(2)Decrescente");
		int ordem = scanner.nextInt();
		System.out.println(" --------------------------------------------");
		Random gerador = new Random();
		for (int i = 0; i < tamanhoPilhas; i++) {
			pilha1.push(gerador.nextInt(101)); // Changed from empilhar to push
		}

		int opcao;
		do {

			System.out.print("Pilha 1: ");
			pilha1.imprimir();
			System.out.print("Pilha 2: ");
			pilha2.imprimir();
			System.out.print("Pilha 3: ");
			pilha3.imprimir();
			if (pilha1.verificar(ordem) + pilha2.verificar(ordem) + pilha3.verificar(ordem) == 5) {
				System.out.println("Voce venceu");
				break;
			}
			System.out.println(" --------------------------------------------");
			System.out.println(" Opcoes:");
			System.out.println("0 - Sair do jogo.");
			System.out.println("1 - Mover.");
			System.out.println("2 - Solucao automatica.");

			System.out.print("Escolha uma opcao: ");

			opcao = scanner.nextInt();
			System.out.println(" --------------------------------------------");

			switch (opcao) {
			case 0:
				System.out.println("Jogo encerrado.");
				break;
			case 1:
				// Mover um elemento de uma pilha para outra.
				System.out.print("Digite o numero da pilha de origem (1, 2 ou 3): ");
				int origem = scanner.nextInt();

				System.out.print("Digite o numero da pilha de destino (1, 2 ou 3): ");
				int destino = scanner.nextInt();

				// Verificar se a origem e o destino sao validos (1, 2 ou 3).
				if (origem < 1 || origem > 3 || destino < 1 || destino > 3) {
					System.out.println("Numero de pilha invalido.");
				} else {
					// Executar a movimentacao se a origem e o destino forem diferentes.
					if (origem != destino) {
						Pilha pilhaOrigem = origem == 1 ? pilha1 : (origem == 2 ? pilha2 : pilha3);
						Pilha pilhaDestino = destino == 1 ? pilha1 : (destino == 2 ? pilha2 : pilha3);

						if (!pilhaOrigem.estaVazia()) {
							int elementoMovido = pilhaOrigem.pop(); // Changed from desempilhar to pop
							pilhaDestino.push(elementoMovido); // Changed from empilhar to push
							pilhaOrigem.incrementarMovimentosExecutados(); // Incrementar a contagem de movimentos
							System.out.println("---------------------------------------------------");
							System.out.println(
									"Numero total de movimentos executados: " + pilhaOrigem.getMovimentosExecutados());
						} else {
							System.out.println("A pilha de origem esta vazia.");
						}
					} else {
						System.out.println("A pilha de origem e destino sao iguais.");
					}
				}
				break;

			case 2:
				if (ordem == 2) {
					ordenarPilhaDecrescenteComPassos(pilha1, pilha3, pilha2);
					break;
				} else if (ordem == 1) {
					ordenarPilhaCrescenteComPassos(pilha1, pilha3, pilha2);
					break;
				}

				break;

			default:
				System.out.println("Opcao invalida.");
				break;
			}
		} while (opcao != 0);

		scanner.close();
	}
}
