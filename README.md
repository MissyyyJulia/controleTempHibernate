# ControleDeTemperatura
Simulação de controle de temperatura para uma estufa automatizada COM PERSISTENCIA NO BANCO - JPA HIBERNATE 

Instruções de execução:
-------
NO TERMINAL DO SEU COMPUTADOR - executar o projeto (Após baixar o projeto):
java -jar nome_projeto.jar

Ao iniciar o programa, deve-se inserir as temperaturas máxima e mínima do dia. Logo após, deve-se inserir a temperatura atual por hora. É possível inserir 24 temperaturas por dia (uma temperatura a cada uma hora). 
Se a temperatura estiver acima da máxima ou abaixo da mínima, um aviso é exibido dizendo que a temperatura está fora do esperado para o dia. 
Após a inserção das temperaturas do dia, é exibida uma lista com as temperaturas registradas no dia e logo depois um resumo das temperaturas coma média, quantidade de temperaturas acima do limite máximo, a maior temperatura registrada e a menor temperatura registrada.

------- 
Atenção: A aplicação incia limpando a tabela do banco para facilitar os testes.
