# Projeto de Refatoração com Implementação de POO - Sistema Clínico

## Objetivos

Implementar os seguintes conceitos:

* Herança
* Polimorfismo
* Encapsulamento
* Abstração
* Collections
* Tratamento de Erros

---
# Regras do Time

* Nunca realizar commits diretamente na `main`.
* Sempre trabalhar em uma branch própria.
* Atualizar sua branch com a `dev` antes de abrir Pull Request.
* Resolver conflitos na sua branch antes do merge.
* Não enviar código que não compile.
* Testar todas as alterações antes de solicitar merge.
* Utilizar mensagens de commit claras e objetivas.


# Estratégia de Branches

## main

* Contém apenas o código final e funcional.
* Não devem ser realizados commits diretos nesta branch.
* Recebe apenas versões validadas da `dev`.

## dev

* Branch de integração do projeto.
* Recebe as implementações concluídas das branches individuais.
* Deve permanecer sempre funcional.

## Branch Pessoal

Cada integrante deve criar sua própria branch a partir da `dev`.

Exemplos:

```bash
feature-joao
feature-giuli
feature-gabriel
```

Nela serão realizados:

* Refatorações;
* Correções;
* Implementação de funcionalidades;
* Testes individuais.

---

# Fluxo de Trabalho

## 1. Clonar o Repositório

```bash
git clone <URL_DO_REPOSITORIO>
cd <NOME_DO_PROJETO>
```

---

## 2. Criar sua Branch

Partindo da branch `dev`:

```bash
git checkout dev
git pull origin dev

git checkout -b feature-seu-nome
```

---

## 3. Desenvolver na sua Branch

Antes de iniciar novas alterações:

```bash
git checkout feature-seu-nome
git pull origin dev
```

Caso existam mudanças recentes na `dev`, atualize sua branch antes de continuar.

---

## 4. Realizar Commits

```bash
git add .
git commit -m "Descrição da alteração"
git push origin feature-seu-nome
```

---

## 5. Testar o Sistema

Antes de enviar para a `dev`:

* Compilar o projeto;
* Executar os testes;
* Verificar se não houve quebra de funcionalidades existentes.

### Se NÃO estiver funcionando

Continuar corrigindo na sua branch.

### Se estiver funcionando

Abrir um Pull Request para a branch `dev`.

---

## 6. Merge para a dev

Após validação:

```text
feature-seu-nome
        ↓
       dev
```

A branch `dev` deve permanecer sempre funcional.

---

## 7. Merge para a main

Quando a `dev` estiver estável e validada pelo grupo:

```text
feature-seu-nome
        ↓
       dev
        ↓
      main
```

A branch `main` deve conter apenas versões finais e funcionais do sistema.

---

# Regras do Time

* Nunca realizar commits diretamente na `main`.
* Sempre trabalhar em uma branch própria.
* Atualizar sua branch com a `dev` antes de abrir Pull Request.
* Resolver conflitos na sua branch antes do merge.
* Não enviar código que não compile.
* Testar todas as alterações antes de solicitar merge.
* Utilizar mensagens de commit claras e objetivas.

