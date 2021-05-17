
public class Codigo {
	private char valor;
	private int quant;
	private String codigo;

	public Codigo(char valor, int quant, String codigo2) {
		this.valor = valor;
		this.quant = quant;
		this.codigo = codigo2;

	}

	public char getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Letra " + this.valor + " Quantidade " + this.quant + " Codigo " + this.codigo;
	}

}
