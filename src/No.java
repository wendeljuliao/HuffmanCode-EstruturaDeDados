public class No {
	public char valor;
	public int quant;
	public String codigo;
	public No proximo;
	public No anterior;
	public No esquerda;
	public No direita;

	public No(char valor, int quant) {
		this.valor = valor;
		this.quant = quant;
		proximo = null;
		anterior = null;
		esquerda = null;
		direita = null;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public boolean isLeaf() {
		return (esquerda == null && direita == null);
	}

	public No getEsquerda() {
		return this.esquerda;
	}

	public No getDireita() {
		return this.direita;
	}

}