package controleTempPersist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controleTempPersist.model.services.FuncoesTemp;

public class Main {

	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("controleTemp");
        EntityManager em = emf.createEntityManager();
        
        //Só pra limpar a tabela antes pra não ter que ficar excluindo toda vez que for testar
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Temperatura").executeUpdate();
        em.getTransaction().commit();

        FuncoesTemp controle = new FuncoesTemp(em);
        controle.inserirTempMaxMin();

        //Permite inserir só 24 temperaturas
        int contador = 1;
        while (contador <= 24) {
            controle.inserirTemperaturas(contador);
            contador++;
        }

        System.out.println("\n------- RESUMO DOS REGISTROS DE HOJE --------");
        controle.imprimirTemperaturas();
        controle.calcularTempMedia();
        controle.calcularTempMinimaMaxima();
        controle.calcularTempAcimaLimiteMaximo();

        em.close();
        emf.close();
    }
}