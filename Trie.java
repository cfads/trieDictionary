import java.util.Scanner;

public class Trie {

	// Tamanho do Alfabeto
	static final int tamanho_Alfabeto = 26;

	// Nó da árvore Trie
	static class TrieNode {
		TrieNode[] filho = new TrieNode[tamanho_Alfabeto];

		// final_Palavra é verdadeiro se o nó for o último da palavra
		boolean final_Palavra;

		TrieNode() {
			final_Palavra = false;
			for (int i = 0; i < tamanho_Alfabeto; i++)
				filho[i] = null;
		}
	};

	static TrieNode root;

	// Se não estiver presente , insere na árvore
	// Se a palavra for o prefixo do nó, marca o nó folha

	static void inserir(String palavra) {
		int i;
		int indice;

		TrieNode trie = root;

		for (i = 0; i < palavra.length(); i++) {
			indice = palavra.charAt(i) - 'a';
			if (trie.filho[indice] == null)
				trie.filho[indice] = new TrieNode();

			trie = trie.filho[indice];
		}

		// marca o ultimo nó como folha
		trie.final_Palavra = true;
	}

	static boolean busca(String palavra) {
		int i;
		int indice;
		TrieNode trie = root;

		for (i = 0; i < palavra.length(); i++) {
			indice = palavra.charAt(i) - 'a';

			if (trie.filho[indice] == null)
				return false;

			trie = trie.filho[indice];
		}

		return (trie != null && trie.final_Palavra);
	}

	public static void main(String args[]) {

		// Arrays
		String output[] = { "não existe no dicionário", "existe no dicionário" };

		// Strings
		String palavra, resposta;

		// Opções
		boolean opc = true, opc2 = true;

		// Construção
		root = new TrieNode();

		// Inserção do dicionário
		Scanner teclado = new Scanner(System.in);

		while (opc == true) {
			int aux;

			System.out.println("\n[0] - sair | Adicione uma palavra: ");
			palavra = teclado.next();

			inserir(palavra);

			System.out.println("\n Deseja inserir novamente ? [1] - Sim  [2] - Não");
			aux = teclado.nextInt();

			if (aux == 2)
				opc = false;

		}

		// Verificação das palavras

		while (opc2 == true) {
			int aux;

			System.out.println("\n\n Digite a palavra que deseja buscar: ");
			resposta = teclado.next();

			if (busca(resposta) == true)
				System.out.println(resposta + " " + output[1]);
			else
				System.out.println(resposta + " " + output[0]);

			System.out.println("\n Deseja buscar novamente ? [1] - Sim  [2] - Não");
			aux = teclado.nextInt();

			if (aux == 2)
				opc2 = false;

		}

		teclado.close();
	}
}
