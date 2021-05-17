
public class NoCodigos {
	Codigo codigo;
	NoCodigos proximo;

	public NoCodigos(Codigo codigo) {
		this.codigo = codigo;
		proximo = null;
	}

	int num;
	String co;

	public NoCodigos(int num, String co) {
		this.num = num;
		this.co = co;
	}

}
