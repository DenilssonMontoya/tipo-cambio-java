DROP TABLE IF EXISTS tipo_cambio;

CREATE TABLE tipo_cambio (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  moneda_origen VARCHAR(250) NOT NULL,
  moneda_destino VARCHAR(250) NOT NULL,
  factor DECIMAL(10,5) DEFAULT NULL
);

INSERT INTO tipo_cambio (moneda_origen, moneda_destino, factor) VALUES
  ('USD', 'PEN', '3.8102'),
  ('PEN', 'USD', '0.2787');