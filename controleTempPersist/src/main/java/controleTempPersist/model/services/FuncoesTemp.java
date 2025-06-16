package controleTempPersist.model.services;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import controleTempPersist.model.entities.Temperatura;

public class FuncoesTemp {

	private int temp_maxima;
	private int temp_minima;

	// EntityManager vem da JPA e cuida da comunicação com o banco
	private EntityManager em;

	public FuncoesTemp() {
	}

	public FuncoesTemp(EntityManager em) {
		this.em = em;
	}

	public void inserirTempMaxMin() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("\nInsira a temperatura Mínima: ");
		temp_minima = scanner.nextInt();
		System.out.print("Insira a temperatura Máxima: ");
		temp_maxima = scanner.nextInt();
	}

	public void inserirTemperaturas(int contador) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("\nInsira a temperatura da " + contador + "ª hora do dia: ");
		int temp_atual = scanner.nextInt();

		// Para criar e salvar as temperaturas no banco
		Temperatura temp = new Temperatura(contador, temp_atual);
		em.getTransaction().begin();
		em.persist(temp);
		em.getTransaction().commit();

		// Se a temperatura estiver fora do padrão
		if (temp_atual > temp_maxima || temp_atual < temp_minima) {
			System.out.println("ATENÇÃO! A temperatura está fora do padrão.");
		}
	}

	public void imprimirTemperaturas() {
		// lista de temperaturas que puxou do banco
		List<Temperatura> temperaturas = em.createQuery("SELECT t FROM Temperatura t ORDER BY t.hora", Temperatura.class).getResultList();

		if (temperaturas.isEmpty()) {
			System.out.println("\nNenhuma temperatura registrada.");
			return;
		}

		System.out.println("\nTemperaturas registradas:");
		for (Temperatura t : temperaturas) {
			System.out.print(t.getValor() + "° (" + t.getHora() + "h), ");
		}
	}

	public void calcularTempMedia() {
		List<Temperatura> temperaturas = em.createQuery("SELECT t FROM Temperatura t", Temperatura.class).getResultList();

		if (temperaturas.isEmpty()) {
			System.out.println("\nNenhuma temperatura registrada.");
			return;
		}

		int soma = 0;
		for (Temperatura temp : temperaturas) {
			soma += temp.getValor();
		}

		int media = soma / temperaturas.size();
		System.out.println("\n\nMédia das temperaturas de hoje: " + media + "°");
	}

	public void calcularTempAcimaLimiteMaximo() {
		List<Temperatura> temperaturas = em.createQuery("SELECT t FROM Temperatura t", Temperatura.class).getResultList();

		int contador = 0;
		for (Temperatura temp : temperaturas) {
			if (temp.getValor() > temp_maxima) {
				contador++;
			}
		}

		System.out.println("\nQuantidades de temperatura acima da máxima: " + contador);
	}

	public void calcularTempMinimaMaxima() {
		List<Temperatura> temperaturas = em.createQuery("SELECT t FROM Temperatura t", Temperatura.class).getResultList();

		if (temperaturas.isEmpty()) {
			System.out.println("Nenhuma temperatura registrada.");
			return;
		}

		int menor = temperaturas.get(0).getValor();
		int maior = temperaturas.get(0).getValor();

		for (Temperatura temp : temperaturas) {
			int valor = temp.getValor();
			if (valor < menor) {
				menor = valor;
			}

			if (valor > maior) {
				maior = valor;
			}
		}

		System.out.println("\nMenor temperatura registrada: " + menor + "°");
		System.out.println("Maior temperatura registrada: " + maior + "°");
	}

}