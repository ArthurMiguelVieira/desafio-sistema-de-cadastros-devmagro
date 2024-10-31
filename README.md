# 4 - DESAFIO - SISTEMA DE CADASTROS

Chega de teoria! Agora você vai botar na prática o primeiro desafio do projeto.

## Escopo do projeto

Neste desafio, você irá colocar em prática os seguintes conhecimentos:

- Orientação a Objetos
- Java IO
- Streams, Lambda
- Exceções
- Boas práticas de código

## A IDEIA DO PROJETO

Você irá criar um sistema de CADASTRO via CLI (no terminal), onde o usuário poderá:

- Cadastrar uma pessoa interessada.
- Alterar dados de um cadastro.
- Deletar uma pessoa.
- Listar todas as pessoas cadastradas.

## Como deverá ser feito?

### Passo 1: Criar o arquivo `formulario.txt`

Crie um arquivo chamado `formulario.txt` com as seguintes perguntas:

1. Qual seu nome completo?
2. Qual seu email de contato?
3. Qual sua idade?
4. Qual sua altura?

A aplicação deve LER este arquivo e PRINTAR as perguntas no terminal.

### Passo 2: Cadastrar um Usuário

Implemente o cadastro de um usuário que responderá às 4 perguntas e imprimirá as respostas no terminal.

### Passo 3: Salvar as Respostas

SALVE as 4 respostas em um arquivo `.txt` no formato `1-LUCASCARRILHO.TXT`, usando letras maiúsculas.

### Passo 4: Menu Principal

Crie um “menu principal” com as seguintes opções:

1. Cadastrar o usuário
2. Listar todos os usuários cadastrados
3. Cadastrar nova pergunta no formulário
4. Deletar pergunta do formulário
5. Pesquisar usuário por nome, idade ou email

### Passo 5: Adicionar e Deletar Perguntas

Implemente a função de adicionar nova pergunta ao formulário e deletar perguntas. Não será possível apagar as 4 primeiras perguntas.

### Passo 6: Buscar Usuários

Crie uma função para BUSCAR usuários cadastrados, permitindo pesquisa por parte do nome ou o termo inteiro.

### Passo 7: Validações

Adicione validações:

1. O usuário deve ter no mínimo 10 caracteres no nome, um `@` no email, ter mais de 18 anos, e a altura deve ser preenchida com números e vírgulas.
2. O cadastro de perguntas deve ser automático.
3. Ao deletar perguntas, o usuário deve indicar apenas o número da pergunta.

