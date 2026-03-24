# Exercício: Caça ao Tesouro com Navegação entre Telas

**Instituição:** Centro Universitário SATC (UNISATC)  
**Curso:** Engenharia de Software  
**Disciplina:** Soluções Mobile  
**Professor:** Thyerri Mezzari  
**Aluno:** Rafael Frassetto Pereira  

---

## Descrição do Projeto

O presente repositório contém o código-fonte referente ao exercício prático "Caça ao Tesouro". O objetivo central do projeto é demonstrar a aplicação e o domínio do framework Jetpack Compose para a construção de interfaces de usuário (UI) declarativas e a implementação da biblioteca Navigation Component para o gerenciamento de fluxo e pilhas de navegação (Back Stack) em um aplicativo Android nativo.

O aplicativo simula um jogo de adivinhação onde o usuário deve avançar por uma série de telas contendo pistas, culminando em uma tela final de conclusão.

## Tecnologias e Dependências

* **Linguagem:** Kotlin
* **SDK Mínimo:** API 24 (Android 7.0)
* **SDK Alvo:** API 36
* **UI Toolkit:** Jetpack Compose (`androidx.compose.ui`)
* **Navegação:** Navigation Compose (`androidx.navigation:navigation-compose:2.7.7`)
* **Material Design:** Material 3 (`androidx.compose.material3`)

## Funcionalidades Implementadas

O projeto atende a todos os requisitos base e extras propostos na especificação da atividade:

1. **Estrutura de Navegação Base:**
   * Utilização de `NavHost` e `NavController` para gerenciar a transição entre 5 telas distintas (Home, Pista 1, Pista 2, Pista 3 e Tesouro).
   * Gerenciamento correto da pilha de retorno (`popBackStack`), permitindo o uso do botão físico de voltar do dispositivo sem quebrar o fluxo da aplicação.

2. **Validação de Entrada de Dados (Requisito Extra):**
   * Implementação de campos de texto (`OutlinedTextField`) nas telas de pistas.
   * Validação lógica que impede o avanço para a próxima tela caso a resposta fornecida esteja incorreta. O sistema trata letras maiúsculas/minúsculas e acentuação básica.

3. **Monitoramento de Tempo (Requisito Extra):**
   * Rastreamento do tempo de execução em milissegundos a partir da interação na tela inicial (`System.currentTimeMillis()`).
   * Exibição do tempo total decorrido (convertido em segundos) na tela final de tesouro.

4. **Animações de Transição (Requisito Extra):**
   * Substituição da transição estática padrão do Jetpack Compose por animações customizadas.
   * Implementação de `slideInHorizontally`, `slideOutHorizontally`, `fadeIn` e `fadeOut` diretamente nas configurações de transição do `NavHost` para entradas e saídas normais e de retorno da pilha.

5. **Reinicialização de Fluxo:**
   * A tela de conclusão permite reiniciar a aplicação retornando à rota inicial ("home") e limpando a pilha de navegação acumulada (`inclusive = false`), garantindo um estado limpo para uma nova execução.

---

## Gabarito das Pistas

Para fins de teste e avaliação da aplicação, abaixo estão as respostas corretas exigidas para avançar em cada uma das telas. A validação no código foi implementada para ignorar diferenças de maiúsculas/minúsculas e acentuação simples.

* **Pista 1:** "O que é, o que é: cai em pé e corre deitado?"
  * **Resposta esperada:** chuva
* **Pista 2:** "Tem cabeça, tem dente, tem barba, não é bicho e não é gente."
  * **Resposta esperada:** alho
* **Pista 3:** "Nasce grande e morre pequeno."
  * **Resposta esperada:** lapis (ou lápis)

---

## Evidência de Funcionamento

Abaixo encontra-se a demonstração em vídeo comprovando a compilação bem-sucedida e o funcionamento de todos os requisitos estipulados na atividade, incluindo navegação, validação, cronômetro e animações.

https://github.com/user-attachments/assets/1785ea2e-1733-4f29-90b9-ef6619d59110
