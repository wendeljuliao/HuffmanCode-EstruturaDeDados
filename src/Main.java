import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		HuffmanArvore huffman = new HuffmanArvore();
		FilaDinamica fila = new FilaDinamica();
		// TabelaCodigos tabela = new TabelaCodigos();
		TabelaCodigos seqArvore = new TabelaCodigos();
		HashLinear hash = new HashLinear(256);
		int[] vetor = new int[256];
		Scanner prompt = new Scanner(System.in);
		Scanner ler = new Scanner(System.in);

		int codigo;
		int opcao;
		

		System.out.println("Digite 1-Compactar 2-Descompactar");
		opcao = prompt.nextInt();
		if (opcao == 1) {
			for (int i = 0; i < vetor.length; i++) {
				vetor[i] = 0;
			}

			System.out.printf("Informe o nome de arquivo texto:\n");
			String nome = ler.nextLine();

			System.out.printf("\nConteúdo do arquivo texto:\n");
			try {
				FileReader arq = new FileReader(nome);
				BufferedReader lerArq = new BufferedReader(arq);

				String linha = lerArq.readLine();
				while (linha != null) {
					// System.out.printf("%s\n", linha);
					for (int i = 0; i < linha.length(); i++) {
						codigo = (int) linha.charAt(i);
						vetor[codigo]++;

					}

					linha = lerArq.readLine();
					if (linha != null) {
						vetor['\n']++;
//						vetor[13]++;
					}
				}

				arq.close();
			} catch (IOException e) {
				System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			}

			for (int i = 0; i < vetor.length; i++) {
				if (vetor[i] > 0) {
					fila.enqueue((char) i, vetor[i]);
				}

			}

			// try {
			// File arq2 = new File("dados.txt");
			// FileWriter escritor = new FileWriter(arq2);
			// huffman.preencher(tabela);
			// for (int i = 0; i < tabela.getTamanho(); i++) {
			// escritor.write(tabela.posicao(i).codigo.toString());
			// escritor.write("\r\n");
			// }
			// escritor.close();
			//
			// } catch (IOException e) {
			// System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			// }
			No numAux;
			No numAux2;
			while (fila.tamanho() > 1) {
				numAux = fila.dequeue();
				numAux2 = fila.dequeue();

				No pai = new No('+', (numAux.quant + numAux2.quant));
				pai.esquerda = numAux;
				pai.direita = numAux2;

				fila.enqueue(pai);

			}
			huffman.inserir(fila.primeiro());
			huffman.mostrar();
			System.out.println();

			huffman.preencherHash(hash);
			hash.imprimir("dados.txt");

			// Parte 2
			try {
				File arq2 = new File("compactado.txt");
				FileWriter escritor = new FileWriter(arq2);
				try {
					FileReader arq = new FileReader(nome);
					BufferedReader lerArq = new BufferedReader(arq);
					huffman.sequenciaArvore(seqArvore);
					for (int i = 0; i < seqArvore.getTamanho(); i++) {
						escritor.write(seqArvore.posicao(i).num + seqArvore.posicao(i).co);
					}
					String linha = lerArq.readLine();
					while (linha != null) {
						// System.out.printf("%s\n", linha);
						for (int i = 0; i < linha.length(); i++) {
							// escritor.write(tabela.comparar(linha.charAt(i)));
							escritor.write(hash.procurar(linha.charAt(i)));
						}

						linha = lerArq.readLine();
						if (linha != null) {
							// escritor.write(tabela.comparar('\n') + "\r");
							escritor.write(hash.procurar('\n'));
//							escritor.write(hash.procurar((char) 13));
						}
					}

					arq.close();
				} catch (IOException e) {
					System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
				}

				escritor.close();

			} catch (IOException e) {
				System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			}

		} else if (opcao == 2) {
			System.out.printf("Informe o nome de arquivo texto:\n");
			String nome = ler.nextLine();

			System.out.printf("\nConteúdo do arquivo texto:\n");
			try {

				FileReader arq = new FileReader(nome);
				BufferedReader lerArq = new BufferedReader(arq);
				try {
					File arqDescompactar = new File("descompactar.txt");
					FileWriter escritor = new FileWriter(arqDescompactar);
					int auxx = 0;
					No arvore = null;
					No aux = null;
					String linha = lerArq.readLine();
					while (linha != null) {
						// System.out.printf("%s\n", linha);
						for (int i = 0; i < linha.length(); i++) {
							if (auxx >= 0) {
								if (arvore == null) {
									arvore = new No('+', 0);
									auxx++;
									huffman.inserir(arvore);
									aux = huffman.raiz;
								} else {
									if (linha.charAt(i) == '1') {
										if (arvore.esquerda == null) {

											arvore.esquerda = new No(Character.valueOf(
													((char) Integer.parseInt(linha.substring(i + 1, i + 9), 2))), 0);
											i = i + 8;
											arvore.esquerda.anterior = arvore;
											auxx--;

										} else if (arvore.direita == null) {

											arvore.direita = new No(Character.valueOf(
													((char) Integer.parseInt(linha.substring(i + 1, i + 9), 2))), 0);
											i = i + 8;
											arvore.direita.anterior = arvore;
											auxx--;
										} else {
											i--;
										}
										if (arvore.esquerda != null && arvore.direita != null) {
											arvore = arvore.anterior;
										}
									} else {
										if (arvore.esquerda == null) {
											arvore.esquerda = new No('+', 0);
											arvore.esquerda.anterior = arvore;
											arvore = arvore.esquerda;
											auxx++;
										} else if (arvore.direita == null) {
											arvore.direita = new No('+', 0);
											arvore.direita.anterior = arvore;
											arvore = arvore.direita;
											auxx++;
										} else {
											i--;
										}
										if (arvore.esquerda != null && arvore.direita != null) {
											arvore = arvore.anterior;
										}

									}
								}

							} else {
								if (linha.charAt(i) == '0') {
									aux = aux.getEsquerda();

								} else {
									aux = aux.getDireita();

								}
								if (aux.isLeaf()) {
									if (aux.valor == '\n' || aux.valor == (char) 13) {

										escritor.write("\r\n");
									} else {

										escritor.write(aux.valor);
									}

									aux = huffman.raiz;
								}
							}
						}

						linha = lerArq.readLine();
					}
					// int auxx = 0;
					// String linha = lerArq.readLine();
					// while (linha != null) {
					// // System.out.printf("%s\n", linha);
					// for (int i = 0; i < linha.length(); i++) {
					//
					// if (auxx >= 0) {
					// if (linha.charAt(i) == '0') {
					// seqArvore.adicionarFinal(new Codigo(linha.charAt(i), 0, "+"));
					// auxx++;
					// } else {
					// auxx--;
					// seqArvore.adicionarFinal(new Codigo(linha.charAt(i), 0, "1"));
					// seqArvore.adicionarFinal(new Codigo(
					// Character.valueOf(((char) Integer.parseInt(linha.substring(i + 1, i + 9),
					// 2))),
					// 0, "C"));
					//
					// i = i + 8;
					// }
					//
					// } else {
					// tabela.adicionarFinal(new Codigo(linha.charAt(i), 0, "C"));
					// }
					// }
					//
					// linha = lerArq.readLine();
					// }
					// seqArvore.exibir();

					escritor.close();
				} catch (IOException e) {
					System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
				}
				arq.close();
			} catch (IOException e) {
				System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			}

			// huffman.criarArvore(seqArvore, null);
			// huffman.criarArvore2(seqArvore);
			// huffman.criarArvore(seqArvore, null);
			// huffman.criarArvore2(seqArvore);

			// huffman.decodeOriginal(tabela, "descompactar.txt");

		}
	}

}
