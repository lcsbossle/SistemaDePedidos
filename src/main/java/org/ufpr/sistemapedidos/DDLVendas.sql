CREATE TABLE cliente(
    id_cliente INT NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(15),
    nome VARCHAR(30),
    sobrenome VARCHAR(30),
    PRIMARY KEY (id_cliente),
    CONSTRAINT cpf_unico UNIQUE(cpf),
    FULLTEXT (nome, sobrenome)
);

CREATE TABLE produto(
    id_produto INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(45),
    PRIMARY KEY (id_produto),
    FULLTEXT (descricao)
);

CREATE TABLE pedido(
    id_pedido INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    data_pedido DATE,
    PRIMARY KEY (id_pedido),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE item_do_pedido(
    id_pedido INT NOT NULL AUTO_INCREMENT,
    id_produto INT,
    qtdade INT,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto),
    UNIQUE KEY(id_pedido, id_produto)
);

