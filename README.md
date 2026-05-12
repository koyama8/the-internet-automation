# The Internet Automation

Projeto de automacao de testes web usando Java, Selenium WebDriver, TestNG, Maven e Allure.

O objetivo e praticar automacao de UI em cima do site [The Internet](https://the-internet.herokuapp.com/), mantendo uma estrutura simples, legivel e proxima de um framework real de testes.

## Stack

- Java 21
- Selenium WebDriver 4.41.0
- TestNG 7.9.0
- Maven
- Allure TestNG 2.24.0
- SLF4J Simple 1.7.36

## O Que O Projeto Cobre

A suite `TheInternetTests` valida fluxos como:

- A/B Testing
- Add/Remove Elements
- Broken Images
- Challenging DOM
- Checkboxes
- Context Menu
- Disappearing Elements
- Drag and Drop
- Dropdown
- Dynamic Content
- Dynamic Controls
- Dynamic Loading
- Entry Ad
- File Download
- Floating Menu
- Forgot Password
- Form Authentication
- Frames
- Horizontal Slider
- Inputs
- Infinite Scroll
- JQuery UI Menus
- JavaScript Alerts
- Key Presses
- Multiple Windows
- Notification Messages
- Redirect Link / Status Codes

## Estrutura

```text
the-internet-automation/
|-- src/
|   |-- test/
|   |   |-- java/
|   |   |   `-- com/koyama/
|   |   |       |-- base/
|   |   |       |   `-- UiTestSupport.java
|   |   |       |-- listeners/
|   |   |       |   `-- ScreenshotListener.java
|   |   |       |-- pages/
|   |   |       |   `-- TheInternetPage.java
|   |   |       `-- tests/
|   |   |           `-- TheInternetTests.java
|   |   `-- resources/
|   |       `-- allure.properties
|-- pom.xml
|-- .gitignore
`-- README.md
```

## Papel Das Classes

- `UiTestSupport`: cria e finaliza o WebDriver, configura o Chrome, define waits e controla diretorio de downloads.
- `TheInternetPage`: page object com seletores, navegacoes, acoes e leituras das paginas do site.
- `TheInternetTests`: suite TestNG com os cenarios automatizados.
- `ScreenshotListener`: listener TestNG que anexa screenshot no Allure quando um teste falha.

## Pre-Requisitos

- Java 21 instalado e configurado no `PATH`.
- Maven instalado e configurado no `PATH`.
- Google Chrome instalado.

O Selenium Manager do Selenium 4 cuida do driver do Chrome automaticamente na maioria dos ambientes.

## Como Rodar

Executar todos os testes:

```powershell
mvn test
```

Executar em modo headless e sem pausas visuais:

```powershell
mvn test "-Dheadless=true" "-Dstudy.mode=false"
```

Executar apenas a suite principal:

```powershell
mvn test "-Dtest=TheInternetTests" "-Dheadless=true" "-Dstudy.mode=false"
```

No PowerShell, os parametros `-D` ficam entre aspas para evitar que propriedades com ponto sejam interpretadas de forma incorreta.

## Configuracoes

- `-Dheadless=true`: executa o Chrome sem abrir janela.
- `-Dstudy.mode=false`: desativa as pausas visuais usadas para acompanhar a execucao durante estudo.
- Sem `-Dstudy.mode=false`, algumas acoes fazem pequenas pausas para facilitar a visualizacao do fluxo.

## Relatorio Allure

Os resultados do Allure sao gerados em:

```text
target/allure-results
```

Para abrir o relatorio localmente, com o Allure CLI instalado:

```powershell
allure serve target/allure-results
```

Ou gerar uma pasta estatica:

```powershell
allure generate target/allure-results -o allure-report --clean
```

## Downloads Durante Os Testes

Os testes que baixam arquivos usam um diretorio temporario dentro de:

```text
target/downloads
```

Esse conteudo e gerado em tempo de execucao e nao deve ser versionado.

## Validacao

Ultima validacao local executada:

```powershell
mvn test "-Dheadless=true" "-Dstudy.mode=false"
```

Resultado esperado:

```text
Tests run: 28, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```
