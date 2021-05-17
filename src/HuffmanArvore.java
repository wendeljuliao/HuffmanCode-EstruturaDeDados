import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HuffmanArvore {
	No raiz;

	public HuffmanArvore() {
		raiz = null;
	}

	public void inserir(No primeiro) {
		if (raiz == null) {
			raiz = primeiro;
		}
	}

	public void mostrar() {
		if (raiz != null) {
			mostrar(raiz, "");
			System.out.println();
		}

	}

	private void mostrar(No raiz, String codigo) {
		if (raiz.isLeaf()) {
			System.out.println(raiz.valor + " " + raiz.quant + " " + codigo);
		}
		if (raiz.esquerda != null) {
			mostrar(raiz.esquerda, codigo + "0");
		}

		if (raiz.direita != null) {
			mostrar(raiz.direita, codigo + "1");
		}
	}

	public void preencherHash(HashLinear hash) {
		if (raiz != null) {
			preencherHash(raiz, "", hash);
		}

	}

	private void preencherHash(No raiz, String codigo, HashLinear hash) {
		if (raiz.isLeaf()) {
			hash.inserir(new Codigo(raiz.valor, raiz.quant, codigo));
			System.out.println(raiz.valor + " " + raiz.quant + " " + codigo);
		}
		if (raiz.esquerda != null) {
			preencherHash(raiz.esquerda, codigo + "0", hash);

		}

		if (raiz.direita != null) {
			preencherHash(raiz.direita, codigo + "1", hash);
		}
	}

	// public void preencher(TabelaCodigos tabelaCodigos) {
	// if (raiz != null) {
	// preencher(raiz, "", tabelaCodigos);
	// }
	//
	// }
	//
	// private void preencher(No raiz, String codigo, TabelaCodigos tabelaCodigos) {
	// if (raiz.isLeaf()) {
	// tabelaCodigos.adicionarFinal(new Codigo(raiz.valor, raiz.quant, codigo));
	// System.out.println(raiz.valor + " " + raiz.quant + " " + codigo);
	// }
	// if (raiz.esquerda != null) {
	// preencher(raiz.esquerda, codigo + "0", tabelaCodigos);
	//
	// }
	//
	// if (raiz.direita != null) {
	// preencher(raiz.direita, codigo + "1", tabelaCodigos);
	// }
	// }

	public void sequenciaArvore(TabelaCodigos seqArvore) {
		if (raiz != null) {
			sequenciaArvore(raiz, "", seqArvore);
			System.out.println();
		}

	}

	private void sequenciaArvore(No raiz, String codigo, TabelaCodigos seqArvore) {
		if (raiz.isLeaf()) {
			// if (raiz.valor == 10) {
			// seqArvore.adicionarFinal(1, "0000" + Integer.toBinaryString(raiz.valor));
			// } else if ((int) raiz.valor < 64) {
			// seqArvore.adicionarFinal(1, "00" + Integer.toBinaryString(raiz.valor));
			// } else if ((int) raiz.valor < 128) {
			// seqArvore.adicionarFinal(1, "0" + Integer.toBinaryString(raiz.valor));
			// } else {
			// seqArvore.adicionarFinal(1, Integer.toBinaryString(raiz.valor));
			// }
			seqArvore.adicionarFinal(1, String.format("%8s", Integer.toBinaryString(raiz.valor)).replace(" ", "0"));
		} else {
			seqArvore.adicionarFinal(0, "");
		}
		if (raiz.esquerda != null) {
			// seqArvore.adicionarFinal(0, "");
			sequenciaArvore(raiz.esquerda, codigo + "0", seqArvore);
		}

		if (raiz.direita != null) {
			// seqArvore.adicionarFinal(0, "");
			sequenciaArvore(raiz.direita, codigo + "1", seqArvore);
		}
	}

	public void criarArvore(TabelaCodigos tab, No anterior) {
		if (raiz == null) {
			if (tab.primeiro().codigo.getValor() != '1') {
				tab.removerInicio();
				raiz = new No('+', 0);
			} else {
				raiz = new No(tab.primeiro().codigo.getValor(), 0);
				tab.removerInicio();
			}
		} else {
			criarArvore(tab, raiz, anterior);
		}
	}

	public void criarArvore(TabelaCodigos tab, No raiz, No anterior) {

		if (tab.getTamanho() == 0) {
			return;
		}
		// while (tab.getTamanho() > 0) {
		if (tab.primeiro().codigo.getValor() == '1') {
			if (raiz.esquerda == null) {
				tab.removerInicio();
				raiz.esquerda = new No(tab.primeiro().codigo.getValor(), 0);
				raiz.esquerda.anterior = raiz;
				tab.removerInicio();
				criarArvore(tab, raiz.esquerda.anterior, raiz.anterior);

			} else if (raiz.direita == null) {
				tab.removerInicio();
				raiz.direita = new No(tab.primeiro().codigo.getValor(), 0);
				raiz.direita.anterior = raiz;
				tab.removerInicio();
				criarArvore(tab, raiz.direita.anterior, raiz.anterior);

			} else {
				criarArvore(tab, raiz.anterior, raiz.anterior);
			}
			// aaa(tab, raiz.anterior, raiz.anterior);
		} else {
			if (raiz.esquerda == null) {
				raiz.esquerda = new No(tab.primeiro().codigo.getValor(), 0);
				raiz.esquerda.anterior = raiz;
				tab.removerInicio();
				criarArvore(tab, raiz.esquerda, raiz);
			} else if (raiz.direita == null) {
				raiz.direita = new No(tab.primeiro().codigo.getValor(), 0);
				raiz.direita.anterior = raiz;
				tab.removerInicio();
				criarArvore(tab, raiz.direita, raiz);
			} else {
				criarArvore(tab, raiz.anterior, raiz.anterior);
			}
		}

		// }

	}

	public void criarArvore2(TabelaCodigos tab) {
		if (raiz == null) {
			if (tab.primeiro().codigo.getValor() != '1') {
				tab.removerInicio();
				raiz = new No('+', 0);
			} else {
				tab.removerInicio();
				raiz = new No(tab.primeiro().codigo.getValor(), 0);

			}
		} else {
			criarArvore2(tab, null);
		}
	}

	public void criarArvore2(TabelaCodigos tab, No anterior) {
		No raiz = this.raiz;
		while (tab.getTamanho() != 0) {
			if (tab.primeiro().codigo.getValor() == '1') {
				if (raiz.esquerda == null) {
					tab.removerInicio();
					raiz.esquerda = new No(tab.primeiro().codigo.getValor(), 0);
					raiz.esquerda.anterior = raiz;
					tab.removerInicio();

				} else if (raiz.direita == null) {
					tab.removerInicio();
					raiz.direita = new No(tab.primeiro().codigo.getValor(), 0);
					raiz.direita.anterior = raiz;
					tab.removerInicio();

				}
				if (raiz.esquerda != null && raiz.direita != null) {
					raiz = raiz.anterior;
				}
			} else {
				if (raiz.esquerda == null) {
					raiz.esquerda = new No(tab.primeiro().codigo.getValor(), 0);
					raiz.esquerda.anterior = raiz;
					raiz = raiz.esquerda;
					tab.removerInicio();

				} else if (raiz.direita == null) {
					raiz.direita = new No(tab.primeiro().codigo.getValor(), 0);
					raiz.direita.anterior = raiz;
					raiz = raiz.direita;
					tab.removerInicio();

				}
				if (raiz.esquerda != null && raiz.direita != null) {
					raiz = raiz.anterior;
				}

			}

		}

	}

	public void decodeOriginal(TabelaCodigos tabela, String nome) {
		No aux = this.raiz;
		try {
			File arq = new File("descompactar.txt");
			FileWriter escritor = new FileWriter(arq);

			for (int i = 0; i < tabela.getTamanho(); i++) {
				if (tabela.posicao(i).codigo.getValor() == '0') {

					aux = aux.getEsquerda();
				} else {

					aux = aux.getDireita();
				}
				if (aux.isLeaf()) {
					if (aux.valor == '\n') {
						escritor.write("\r\n");
					} else {
						escritor.write(aux.valor);
					}

					aux = this.raiz;
				}
			}
			escritor.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

	}

}
