Requisitos:
-  Deve ser executada em linha de comando 

Classes:

- Biblioteca: Responsável pelo gerenciamento dos arquivos;

Operações: 
- Construtor: utilizar SQLite pra persistência de dados;
- Deletar entradas na biblioteca; 
- Editar entradas da biblioteca; 
- Listar entradas na biblioteca; 
- Criar novas bibliotecas;
- Alternar entre bibliotecas; 
- Deletar bibliotecas.

- Documento: Classe abstrata e generalizada cujos filhos serão implementações mais específicas de acordo com o tipo de documento e arquivo (ex.: Livro, Notas de Slide, etc.). Tem que ter os metadados especificados no documento do trabalho;

1. A aplicação usará uma estrutura de diretórios para organizar os arquivos
PDF. A aplicação deve poder criar um diretório raiz (Nomeado personalizadamente pelo usuário) que será a “biblioteca” de arquivos. Dentro de tal
diretório, os arquivos PDF do usuário serão organizados em subdiretórios,
organizados por nome do autor, ou tipo do arquivo (se é PDF de livro,
nota de aula, etc.).

Tasks:

- Criar diretórios baseados no tipo de arquivo dentro do /library