USE onlinestore;

-- 1) Desactivar comprobación de FKs para truncar
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE pedido;
TRUNCATE TABLE cliente;
TRUNCATE TABLE articulo;

-- 2) Volver a activar FKs
SET FOREIGN_KEY_CHECKS = 1;

-- 3) POBLAR ARTÍCULOS
INSERT INTO articulo (codigoArticulo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion) VALUES
                                                                                                    ('A001','Smartphone X100 64GB',       299.99,  5.00, 2),
                                                                                                    ('A002','Portátil Ultraligero 14\"',  799.00, 10.00, 5),
                                                                                                    ('A003','Auriculares Inalámbricos Z',  59.50,  3.50, 1),
                                                                                                    ('A004','Monitor 24\" Full HD',      129.99,  7.00, 3),
                                                                                                    ('A005','Teclado Mecánico RGB',       49.90,  4.00, 1),
                                                                                                    ('A006','Ratón Óptico Gaming',        39.99,  4.00, 1),
                                                                                                    ('A007','Disco Duro Externo 1TB',     64.95,  6.00, 2),
                                                                                                    ('A008','Memoria RAM 8GB DDR4',       34.99,  2.50, 1),
                                                                                                    ('A009','SSD 500GB NVMe',             89.90,  5.00, 2),
                                                                                                    ('A010','Smartwatch Series 5',       199.00,  5.00, 2),
                                                                                                    ('A011','Tablet 10\" HD',             149.99,  5.00, 3),
                                                                                                    ('A012','Impresora Láser Multifunción',129.00, 8.00, 4),
                                                                                                    ('A013','Cámara Réflex 24MP',       549.00, 12.00, 7),
                                                                                                    ('A014','Objetivo 50mm f/1.8',       119.00,  6.00, 2),
                                                                                                    ('A015','Trípode Fotográfico',        29.99,  3.00, 1),
                                                                                                    ('A016','Libro “Java Avanzado”',      39.90,  2.00, 1),
                                                                                                    ('A017','Juego de Mesa “Estrategia”',24.95,  3.50, 1),
                                                                                                    ('A018','Camiseta Deportiva L',       19.99,  2.50, 1),
                                                                                                    ('A019','Zapatillas Running T42',     69.99,  4.50, 2),
                                                                                                    ('A020','Mochila Urban Tech',         49.90,  3.00, 2),
                                                                                                    ('A021','Lámpara LED Escritorio',     22.50,  2.50, 1),
                                                                                                    ('A022','Cargador USB‑C 65W',         29.99,  2.00, 1),
                                                                                                    ('A023','Batería Externa 10000mAh',   24.99,  3.00, 1),
                                                                                                    ('A024','Soporte Portátil Plegable',  34.90,  3.50, 1),
                                                                                                    ('A025','Cable HDMI 2m',               9.99,  1.50, 1),
                                                                                                    ('A026','Micrófono USB Condensador',  79.99,  5.00, 2),
                                                                                                    ('A027','Webcam 1080p',               49.90,  3.00, 1),
                                                                                                    ('A028','Router WiFi 6 Dual Band',    129.90,  7.00, 3),
                                                                                                    ('A029','Switch Ethernet 8 puertos',  39.90,  4.00, 1),
                                                                                                    ('A030','Disco Duro SSD 1TB',        159.99,  5.00, 2);

-- 4) POBLAR CLIENTES (tipoCliente = 'PREMIUM' o 'ESTANDAR' tal cual aparece en el ENUM)
INSERT INTO cliente (email, nif, nombre, domicilio, tipoCliente, descuento, cuotaAnual) VALUES
-- PREMIUM
('joan.martinez@uoc.com','12345678A','Joan Martínez','Carrer de Balmes, 123','PREMIUM',   10, 50.00),
('laia.puig@uoc.com',    '23456789B','Laia Puig',    'Ronda Sant Antoni, 45','PREMIUM',   15, 75.00),
('oriol.costa@uoc.com',  '34567890C','Oriol Costa',  'Avinguda Diagonal, 200','PREMIUM',   20,100.00),
('marta.riera@uoc.com',  '45678901D','Marta Riera',  'Passeig de Gràcia, 50','PREMIUM',   12, 60.00),
('pau.soler@uoc.com',    '56789012E','Pau Soler',    'Carrer de la Marina, 67','PREMIUM',   18, 90.00),
-- ESTANDAR
('nuria.vidal@uoc.com',  '67890123F','Núria Vidal',  'Carrer del Parlament, 10','ESTANDAR',  0,  0.00),
('jordi.ferrer@uoc.com', '78901234G','Jordi Ferrer', 'Gran Via de les Corts Catalanes,520','ESTANDAR',0, 0.00),
('carla.romero@uoc.com', '89012345H','Carla Romero', 'Carrer de Sants, 300','ESTANDAR',   0,  0.00),
('marc.serra@uoc.com',   '90123456J','Marc Serra',   'Carrer de Mallorca, 250','ESTANDAR',   0,  0.00),
('helena.gomez@uoc.com', '01234567K','Helena Gómez', 'Carrer de Verdi, 15','ESTANDAR',     0,  0.00);

-- 5) POBLAR PEDIDOS
INSERT INTO pedido (
    numeroPedido,
    codigoArticulo,
    emailCliente,
    cantidadArticulos,
    precioTotal,
    fechaPedido,
    estado
) VALUES
      ( 1,'A001','joan.martinez@uoc.com',   1,  299.99,'2025-04-01 10:00:00','PENDIENTE'),
      ( 2,'A002','laia.puig@uoc.com',       2, 1598.00,'2025-04-02 11:30:00','PENDIENTE'),
      ( 3,'A003','oriol.costa@uoc.com',     3,  178.50,'2025-04-03 13:45:00','ENVIADO'),
      ( 4,'A005','marta.riera@uoc.com',     1,   49.90,'2025-04-04 09:15:00','PENDIENTE'),
      ( 5,'A010','pau.soler@uoc.com',       2,  398.00,'2025-04-05 14:20:00','ENVIADO'),
      ( 6,'A012','nuria.vidal@uoc.com',     1,  129.00,'2025-04-06 16:00:00','PENDIENTE'),
      ( 7,'A015','jordi.ferrer@uoc.com',    4,  119.96,'2025-04-07 12:10:00','ENVIADO'),
      ( 8,'A020','carla.romero@uoc.com',    1,   49.90,'2025-04-08 08:50:00','PENDIENTE'),
      ( 9,'A025','marc.serra@uoc.com',      2,   19.98,'2025-04-09 15:35:00','ENVIADO'),
      (10,'A030','helena.gomez@uoc.com',    1,  159.99,'2025-04-10 17:25:00','PENDIENTE'),
      (11,'A007','joan.martinez@uoc.com',   3,  194.85,'2025-04-11 10:05:00','ENVIADO'),
      (12,'A014','laia.puig@uoc.com',       2,  238.00,'2025-04-12 11:40:00','PENDIENTE'),
      (13,'A018','oriol.costa@uoc.com',     5,   99.95,'2025-04-13 13:00:00','ENVIADO'),
      (14,'A022','marta.riera@uoc.com',     2,   59.98,'2025-04-14 09:30:00','PENDIENTE'),
      (15,'A028','pau.soler@uoc.com',       1,  129.90,'2025-04-15 14:15:00','ENVIADO');
