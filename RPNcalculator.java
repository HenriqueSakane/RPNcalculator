import java.math.BigDecimal;
import java.util.*;

public class RPNcalculator {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Escreva o numero de caracteres da sua expressao RPN:");
		int numero = in.nextInt();
		System.out.printf("Agora escreva os %d caracteres separando eles com (Enter):",numero);
		// variavel que vai ser concatenada
		String concatenado = "";
		for (int i = 0; i < numero; i++) {
			String aux = in.next();
			if (concatenado.equals("")) {
				concatenado = concatenado + aux;
			} else {
				concatenado = (concatenado + " ") + aux;
			}
		}
		BigDecimal func = func(concatenado);
		System.out.println("Resultado: " + func);
	}

	// iniciar a pilha
	static Stack<String> calculadora = new Stack<String>();

	public static BigDecimal func(String equacao) {
		// separar os tokens por espaços
		String tokens[] = equacao.split("\\ ");
		for (String token : tokens) {
			if ("+-*/^".contains(token)) {
				// craidno o operador
				BigDecimal n1 = new BigDecimal(calculadora.pop());
				BigDecimal n2 = new BigDecimal(calculadora.pop());
				// check o token
				switch (token) {
				case "+":
					calculadora.push(n1.add(n2).toString());
					break;
				case "-":
					calculadora.push(n1.subtract(n2).toString());
					break;
				case "*":
					calculadora.push(n1.multiply(n2).toString());
					break;
				case "/":
					calculadora.push(n1.divide(n2).toString());
					break;
				case "^":
					calculadora.push(n1.pow(n2.intValue()).toString());
					break;
				}
			} else {
				if (ehNumber(token)) {
					calculadora.push(token);
				} else {
					System.out.println("Erro na escrita");
				}
			}

		}
		String resultado = calculadora.pop();
		if (ehNumber(resultado)) {
			return new BigDecimal(resultado);
		} else {
			System.out.println("Erro na escrita");
		}
		System.out.println("Erro");
		return BigDecimal.ZERO;

	}

	public static boolean ehNumber(String match) {
		return match.matches("[0-9.]*");
	}

}
