CREATE TABLE Comentario(
	Id BIGINT NOT NULL AUTO_INCREMENT,
    Ordem_Servico_Id BIGINT NOT NULL,
    Descricao TEXT NOT NULL,
    Data_Envio DATETIME NOT NULL,
    
    PRIMARY KEY(Id)
);

ALTER TABLE Comentario ADD CONSTRAINT fk_comentario_ordem_servico
FOREIGN KEY (Ordem_Servico_Id) REFERENCES Ordem_Servico(Id);