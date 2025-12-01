# Financeiro Pessoal - JavaFX

Este projeto é uma aplicação desktop desenvolvida em **Java** utilizando **JavaFX**, com foco no gerenciamento simples de transações financeiras (receitas e despesas). A aplicação permite cadastrar, listar e organizar transações, oferecendo uma interface amigável e de fácil navegação.

---

## 📌 **Descrição Geral do Projeto**

A aplicação possui as seguintes funcionalidades:

* Cadastro de transações com **descrição**, **valor**, **data** e **tipo** (Receita ou Despesa).
* Visualização de transações em uma **tabela central**, organizada e atualizada automaticamente.
* Separação clara entre camadas do projeto (**Model**, **Service**, **Controller**, **Util**).
* Estrutura baseada em **FXML**, permitindo interface modular e flexível.

O objetivo é oferecer uma introdução prática ao uso de JavaFX com boas práticas de organização de código.

---

## 🛠️ **Como Executar o Projeto**

Siga os passos abaixo para rodar a aplicação na sua máquina.

---

## ▶️ **Executando com Gradle (via `gradlew`)**

Se o projeto já contém os arquivos `gradlew` e `gradlew.bat`, você pode rodar a aplicação sem instalar o Gradle manualmente.

### **1. Dar permissão ao gradlew (Linux/macOS)**

```bash
chmod +x gradlew
```

### **2. Rodar o projeto**

No diretório raiz do projeto:

```bash
./gradlew run       # Linux/macOS
```

```bash
gradlew.bat run     # Windows
```

### **3. Configuração do JavaFX com Gradle**

Certifique-se de que o arquivo `build.gradle` contém algo semelhante a:

```gradle
javafx {
    version = "17"
    modules = [ 'javafx.controls', 'javafx.fxml' ]
}
```

Se já estiver configurado, basta executar o comando **run** normalmente.

---

## 🛠️ **Como Executar o Projeto (Manual)**

Siga os passos abaixo para rodar a aplicação na sua máquina:

### 1. **Pré-requisitos**

Antes de iniciar, instale:

* **Java 17 ou superior** (versão LTS recomendada)
* **JavaFX SDK compatível com sua versão do Java**
* **IntelliJ IDEA** (recomendado) ou Eclipse/NetBeans
* **Maven** (se o projeto não vier configurado com ele)

### 2. **Clonar ou baixar o repositório**

Você pode baixar via ZIP ou usar:

```bash
git clone https://github.com/JoaoMartins90/Financeiro-Pessoal---JavaFX.git
```

### 3. **Configurar o JavaFX no IntelliJ**

1. Acesse **File > Project Structure > Libraries**
2. Clique em **+** e selecione a pasta `lib` dentro do JavaFX SDK instalado
3. Em **Run > Edit Configurations**, adicione em *VM Options*:

```
--module-path /caminho/para/javafx-sdk-XX/lib --add-modules javafx.controls,javafx.fxml
```

### 4. **Rodar a aplicação**

Abra o arquivo principal do projeto, normalmente chamado de:

```
MainApplication.java
```

E execute.

---

## 🏗️ **Estrutura do Projeto**

```
src/
 └── main/java/com/example/financeiro/
       ├── controller/      -> Controladores FXML e lógica da interface
       ├── model/           -> Classes de modelo (Transaction, etc.)
       ├── service/         -> Regras de negócio e manipulação de dados
       ├── util/            -> Classes utilitárias (como FXMLUtils)
       └── MainApplication  -> Classe de inicialização

resources/
 └── fxml/                  -> Telas FXML da interface
```

---

## 👥 **Autores**

* **João Martins** 

---
