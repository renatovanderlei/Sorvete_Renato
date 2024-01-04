# Sorvete
Projeto com intuito de criação e validação de sabores e tipo de sorvete, feito em Angular e Java utilizando spring boot como framework, usando MySQL Workbench no banco de dados.

##Iniciar o Front:
	- Feito no VsCode: Terminal -> npm install --force
				    -> npm start

##Iniciar o Back:
	*Após iniciar o banco de dados;
	- Feito no Eclipse: - Realizar configurações necessárias no ConnectionManager.java
	- Iniciar a aplicação a partir do SorveteApplication.java

## Registro de Sabor:
    -> Permite cadastrar/editar/atualizar/deletar novos sabores de sorvete.


## Registro de TipoSorvete:
    -> Permite cadastrar/editar/atualizar/deletar os tipos de sorvetes, definindo a quantidade máxima de sabores permitidos para cada tipo.
## Registro de Sorvete:
    -> Relaciona o sorvete ao TipoSorvete.
    -> Relaciona o sorvete à lista de Sabores.
    -> Limita a quantidade de sabores registrados pelo TipoSorvete escolhido.
## Relatório por dia:
    -> Emite um relatório com a quantidade de sorvetes vendidos no dia.
    -> Inclui informações sobre quantos sorvetes foram vendidos por sabor no dia.

    
## Observações Importantes
    -> Os relacionamentos devem ser feitos entre as classes, mantendo a orientação a objetos.
    -> É necessário implementar CRUD para as entidades Sabor e TipoSorvete.
    -> O projeto deve utilizar a implementação Restful do Spring para o desenvolvimento e os dados devem ser armazenados em banco de dados.
    -> A quantidade de sabores registrados não pode ultrapassar a quantidade estipulada no registro de TipoSorvete.
