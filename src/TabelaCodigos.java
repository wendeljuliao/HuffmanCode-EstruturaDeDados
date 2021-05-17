
public class TabelaCodigos {

	private NoCodigos primeiro;
	private NoCodigos ultimo;
	private int tamanho;

	public TabelaCodigos() {
		primeiro = null;
		ultimo = null;
		tamanho = 0;
	}

	public void adicionarFinal(Codigo valor) {
		NoCodigos novo = new NoCodigos(valor);

		if (primeiro == null) {
			primeiro = novo;
			ultimo = novo;
		} else {
			ultimo.proximo = novo;
			ultimo = novo;
		}
		tamanho++;
	}

	public void adicionarFinal(int num, String co) {
		NoCodigos novo = new NoCodigos(num, co);

		if (primeiro == null) {
			primeiro = novo;
			ultimo = novo;
		} else {
			ultimo.proximo = novo;
			ultimo = novo;
		}
		tamanho++;
	}

	public void adicionarInicio(Codigo valor) {
		NoCodigos novo = new NoCodigos(valor);

		if (primeiro == null) {
			primeiro = novo;
			ultimo = novo;
		} else {
			novo.proximo = primeiro;
			primeiro = novo;
		}
		tamanho++;
	}

	public void removerInicio() {
		if (primeiro != null) {
			if (primeiro == ultimo) {
				primeiro = null;
				ultimo = null;
			} else {
				NoCodigos aux = primeiro;
				primeiro = primeiro.proximo;
				aux.proximo = null;
			}
			tamanho--;
		}
	}

	public void removerFinal() {
		if (ultimo != null) {
			if (primeiro == ultimo) {
				primeiro = null;
				ultimo = null;
			} else {
				NoCodigos atual = primeiro;

				while (atual.proximo != ultimo) {
					atual = atual.proximo;
				}

				ultimo = atual;
				ultimo.proximo = null;
			}
			tamanho--;
		}
	}

	public NoCodigos posicao(int pos) {
		NoCodigos atual = primeiro;
		int i = 0;
		while (atual != null && i != pos) {
			i++;
			atual = atual.proximo;
		}
		return atual;

	}

	public int getTamanho() {
		return this.tamanho;
	}

	public NoCodigos primeiro() {
		return this.primeiro;
	}

	// public boolean remover(String nome) {
	// NoCodigos atual = primeiro;
	// NoCodigos anterior = null;
	// while (atual != null && !atual.valor.getNome().equals(nome)) {
	// anterior = atual;
	// atual = atual.proximo;
	// }
	// if (atual.valor.getNome().equals(nome)) {
	// if (primeiro == ultimo) {
	// primeiro = null;
	// ultimo = null;
	// } else if (anterior == null) {
	// primeiro = atual.proximo;
	// } else {
	// anterior.proximo = atual.proximo;
	// }
	// if (atual.proximo == null) {
	// ultimo = anterior;
	// }
	// tamanho--;
	// return true;
	// }
	// return false;
	// }

	public String comparar(char letra) {
		NoCodigos atual = primeiro;

		while (atual != null) {
			if (atual.codigo.getValor() == letra) {
				return atual.codigo.getCodigo();
			}
			atual = atual.proximo;
		}
		return "";
	}

	public void exibir() {
		NoCodigos atual = primeiro;

		while (atual != null) {
			System.out.print(atual.codigo + " ");
			atual = atual.proximo;
		}

		System.out.println();
	}

}
