public class FilaDinamica {
	private No primeiro;
	private No ultimo;
	private int tamanho;

	public FilaDinamica() {
		primeiro = null;
		ultimo = null;
		tamanho = 0;
	}

	public void enqueue(char item, int quant) {
		No novo = new No(item, quant);
		if (primeiro == null) {
			primeiro = novo;
			ultimo = novo;
		} else {
			No atual = primeiro;
			while (atual != null && novo.quant >= atual.quant) {
				atual = atual.proximo;
			}
			if (atual == primeiro) {
				novo.proximo = primeiro;
				primeiro.anterior = novo;
				primeiro = novo;
			} else if (atual == null) {
				ultimo.proximo = novo;
				novo.anterior = ultimo;
				ultimo = novo;
			} else {
				novo.proximo = atual;
				atual.anterior.proximo = novo;
				novo.anterior = atual.anterior;
				atual.anterior = novo;
			}
		}
		tamanho++;
	}

	public void enqueue(No no) {
		if (primeiro == null) {
			primeiro = no;
			ultimo = no;
		} else {
			No atual = primeiro;
			while (atual != null && no.quant >= atual.quant) {
				atual = atual.proximo;
			}
			if (atual == primeiro) {
				no.proximo = primeiro;
				primeiro.anterior = no;
				primeiro = no;
			} else if (atual == null) {
				ultimo.proximo = no;
				no.anterior = ultimo;
				ultimo = no;
			} else {
				no.proximo = atual;
				atual.anterior.proximo = no;
				no.anterior = atual.anterior;
				atual.anterior = no;
			}
		}
		tamanho++;
	}

	public No dequeue() {
		if (primeiro != null) {
			No item = primeiro;

			primeiro = primeiro.proximo;
			item.proximo = null;
			tamanho--;
			return item;
		}
		return null;
	}

	public No primeiro() {
		return primeiro;
	}

	public int tamanho() {
		return this.tamanho;

	}

	public boolean umELemento() {
		return (primeiro == ultimo);
	}

	// public void exibir2() {
	// No atual = primeiro;
	//
	// while (atual != null) {
	// atual.arvore.exibirPosOrdem();
	// atual = atual.proximo;
	//
	// }
	//
	// System.out.println();
	// }

	public void exibirNormal() {
		No atual = primeiro;

		while (atual != null) {
			System.out.print(atual.valor + " " + atual.quant);
			atual = atual.proximo;
		}

		System.out.println();

	}

	// public void exibir() {
	// No atual = primeiro;
	//
	// while (atual != null) {
	// if (atual.arvore == null) {
	// System.out.print(atual.valor + " " + atual.quant);
	// } else {
	// atual.arvore.exibirPosOrdem();
	// }
	// atual = atual.proximo;
	// System.out.println();
	// }
	//
	// System.out.println();
	// }
}