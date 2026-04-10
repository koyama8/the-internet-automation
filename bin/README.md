# 🤖 The Internet Automation

<p align="center">
  Automação web com <strong>Java, Selenium, TestNG, Maven e Allure</strong>,
  construída com foco em organização, prática de mercado e evolução contínua.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium" />
  <img src="https://img.shields.io/badge/TestNG-FF6F00?style=for-the-badge" alt="TestNG" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/Allure-8A2BE2?style=for-the-badge" alt="Allure Report" />
</p>

---

## 📌 Overview

Projeto de automação de testes web baseado no site **The Internet**, com estrutura voltada para estudo prático e evolução para um framework mais profissional.

O projeto utiliza:
- Selenium WebDriver para automação da interface
- TestNG para organização e execução dos testes
- Maven para build e gerenciamento de dependências
- Allure Report para geração de relatórios

---

## 🧰 Stack

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- Allure Report

---

## 📁 Estrutura

```bash
the-internet-automation/
├── src/test/java/com/koyama/
│   ├── base/
│   │   └── BaseTest.java
│   ├── listeners/
│   │   └── ScreenshotListener.java
│   ├── pages/
│   │   └── TheInternetPage.java
│   └── tests/
│       └── BasicElementsTests.java
├── src/test/resources/
│   └── allure.properties
├── drivers/
├── pom.xml
├── .gitignore
└── README.md
```

---

## ▶️ Execução

Na raiz do projeto, execute:

```bash
mvn clean test
```

---

## 📊 Relatório

Após a execução dos testes, abra o relatório com:

```bash
allure serve target/allure-results
```

---

## 🎯 Objetivo

Este repositório foi criado para consolidar prática em automação web com Java e evoluir gradualmente a arquitetura do projeto, aproximando-o de um cenário mais real de mercado.

---

## 🚧 Status

Projeto em evolução contínua, com foco em melhoria da estrutura, expansão de cenários automatizados e refinamento da abordagem de automação.
