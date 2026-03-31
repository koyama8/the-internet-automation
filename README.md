# 🤖 The Internet Automation

<p align="center">
  Projeto de automação web com <strong>Java, Selenium, TestNG e Maven</strong>, construído com foco em boas práticas, organização escalável e evolução contínua da arquitetura de testes.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium" />
  <img src="https://img.shields.io/badge/TestNG-FF6F00?style=for-the-badge&logo=java&logoColor=white" alt="TestNG" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/QA_Automation-0A66C2?style=for-the-badge&logo=testinglibrary&logoColor=white" alt="QA Automation" />
</p>

---

## 📌 Visão do projeto

Este repositório foi criado para desenvolver uma base sólida em **automação de testes web**, utilizando o site **The Internet** como ambiente prático para validar fluxos, elementos, comportamentos dinâmicos e cenários comuns do dia a dia de QA.

O objetivo é construir um projeto simples no início, mas com estrutura preparada para evoluir para um framework mais profissional e escalável.

---

## 🏗 Arquitetura

A proposta da arquitetura segue uma linha de crescimento progressivo:

- **tests** → cenários automatizados
- **base** → configuração e ciclo de vida do driver
- **pages** → abstração das páginas
- **utils** → métodos auxiliares e reaproveitamento
- **data** → massa de dados e apoio aos testes

---

## 📁 Estrutura

```bash
the-internet-automation/
├── src/test/java/com/koyama/
│   └── tests/
├── drivers/
├── pom.xml
└── README.md
