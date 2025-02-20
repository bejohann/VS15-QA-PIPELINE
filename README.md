# 🚀 Projeto de Testes REST Assured com CI/CD

## 📌 Sobre o Projeto
Este repositório contém uma estrutura de testes automatizados para APIs utilizando RestAssured com integração contínua e pipeline de CI/CD via GitHub Actions. 
Os testes são categorizados em Health Check, Contrato e Funcionais, com geração de relatórios no Allure e notificações para o Discord.

## 😎 Integrantes da equipe
- **Time 09:**
    - **[Bernardo Johann](https://github.com/bejohann)**
    - **[Renato Campos](https://github.com/qqwewe)**
 
## 🛠️ Tecnologias Utilizadas

- Java 17
- RestAssured
- JUnit
- Maven
- GitHub Actions
- Allure Reports
- CodeQL Analysis

## 📜 Estrutura da Pipeline
O fluxo de CI/CD está estruturado conforme a sequência abaixo:

1. **Build:** Configuração do ambiente e instalação das dependências Maven.
2. **Health-check:** Validação básica da API para garantir que está operacional.
3. **Contract-test:** Testes de contrato para verificar se a API segue o esperado.
4. **Functional-test:** Testes funcionais que validam cenários de uso.
5. **Allure-report:** Geração e publicação do relatório de testes no GitHub Pages.
6. **Discord-report:** Envio de notificações para um canal do Discord.
7. **CodeQL Analysis:** Análise estática para segurança do código.

## 🔹 Endpoints Testados
Os testes foram realizados sobre os seguintes endpoints:

- **POST:** `/login`, `/usuarios` e `/produtos`
- **GET:** `/usuarios` e `/usuarios/{_id}`
- **PUT:** `/usuarios/{_id}`
- **DELETE:** `/usuarios/{_id}`

## 🔍 Funcionalidades Testadas
Os testes cobrem as principais funcionalidades da API, garantindo que:

- Seja possível realizar login através do endpoint `/login`.
- O sistema permita cadastrar novos usuários utilizando `/usuarios`.
- Produtos possam ser cadastrados por meio do endpoint `/produtos`.
- Os usuários possam ser listados através de `/usuarios` e `/usuarios/{_id}`.
- O endpoint `/usuarios/{_id}` possibilite editar as informações de um usuário.
- Usuários possam ser excluídos com sucesso utilizando `/usuarios/{_id}`.

## 📊 Relatórios de Testes

Os resultados dos testes são processados pelo Allure Report e publicados automaticamente. Para acessar a versão mais recente do relatório, clique no link:
🔗 [Allure Report](https://bejohann.github.io/VS15-QA-PIPELINE/)

## 📢 Notificações no Discord
Após cada execução da pipeline, um relatório é enviado para um canal do Discord, informando o status de cada job executado.
