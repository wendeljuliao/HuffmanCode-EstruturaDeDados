import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class HashLinear {
	private Hash tabela[];
	private int TAM_MAX;
	private int tam;

	public HashLinear(int n) {
		tabela = new Hash[n];
		TAM_MAX = n;
		tam = 0;
		for (int i = 0; i < n; i++) {
			tabela[i] = new Hash(false);
		}

	}

	private int funcaoHash(int numero) {
		return numero % TAM_MAX;
	}

	public void inserir(Codigo codigo) {
		int pos = funcaoHash(codigo.getValor());
		tabela[pos].codigo = codigo;
		tabela[pos].ocupado = true;
		tam++;
	}

	public String procurar(char a) {
		int pos = funcaoHash((int) a);
		if (tabela[pos].ocupado == true) {
			return tabela[pos].codigo.getCodigo();
		}
		return "";
	}

	public void mostrar() {
		for (int i = 0; i < TAM_MAX; i++) {
			if (tabela[i].ocupado == true) {
				System.out.print(tabela[i].codigo.toString() + " ");
			}
		}
	}
	
	public void imprimir(String nome) {
		try {
			File arq = new File(nome);
			FileWriter escritor = new FileWriter(arq);
			
			for (int i = 0; i < TAM_MAX; i++) {
				if (tabela[i].ocupado == true) {
					escritor.write(tabela[i].codigo.toString());
					escritor.write("\r\n");
				}
			}
			escritor.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

	}

}
