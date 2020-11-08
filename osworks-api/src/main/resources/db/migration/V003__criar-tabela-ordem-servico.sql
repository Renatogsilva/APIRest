CREATE TABLE ordem_servico(
	Id BIGINT NOT NULL AUTO_INCREMENT,
    Cliente_Id BIGINT NOT NULL,
    Descricao TEXT NOT NULL,
    Preco DECIMAL(10,2) NOT NULL,
    Status VARCHAR(20) NOT NULL,
    Data_abertura DATETIME NOT NULL,
    Data_finalizacao DATETIME,
    
    PRIMARY KEY (ID)
);

ALTER TABLE ordem_servico ADD CONSTRAINT fk_ordem_servico_cliente
FOREIGN KEY (Cliente_Id) REFERENCES Cliente (Id)