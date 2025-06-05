Classes:

- Biblioteca: Responsável pelo gerenciamento dos arquivos;

Operações: 
- Deletar entradas na biblioteca, 
- Editar entradas da
- biblioteca, 
- Listar entradas na biblioteca, 
- Criar novas bibliotecas,
- Alternar entre bibliotecas, 
- Deletar bibliotecas.

A biblioteca vai utilizar SQLite pra persistência de dados;

- Documento: Classe abstrata e generalizada cujos filhos serão implementações mais específicas de acordo com o tipo de documento e arquivo (ex.: Livro, Notas de Slide, etc.). Tem que ter os metadados especificados no documento do trabalho;

- 