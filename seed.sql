USE newproject;

-- ============================================================
-- ROLES
-- ============================================================
INSERT INTO rol (IDROL, NOMBRE) VALUES (1, 'ADMIN');
INSERT INTO rol (IDROL, NOMBRE) VALUES (2, 'CLIENTE');

-- ============================================================
-- PERMISOS
-- ============================================================
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (1, 'CREAR_PRODUCTO', 'Permite crear nuevos productos');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (2, 'EDITAR_PRODUCTO', 'Permite modificar productos existentes');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (3, 'ELIMINAR_PRODUCTO', 'Permite eliminar productos');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (4, 'VER_PRODUCTOS', 'Permite visualizar productos');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (5, 'GESTIONAR_USUARIOS', 'Permite gestionar usuarios del sistema');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (6, 'GESTIONAR_PEDIDOS', 'Permite gestionar pedidos');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (7, 'GESTIONAR_CATEGORIAS', 'Permite gestionar categorias');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (8, 'GESTIONAR_DESCUENTOS', 'Permite gestionar descuentos');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (9, 'VER_VENTAS', 'Permite visualizar ventas');
INSERT INTO permiso (IDPERMISO, NOMBRE, DESCRIPCION) VALUES (10, 'GESTIONAR_ENVIOS', 'Permite gestionar envios');

-- ============================================================
-- ROL_PERMISO (Admin tiene todos los permisos)
-- ============================================================
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (1, 1, 1);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (2, 1, 2);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (3, 1, 3);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (4, 1, 4);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (5, 1, 5);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (6, 1, 6);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (7, 1, 7);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (8, 1, 8);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (9, 1, 9);
INSERT INTO rol_permiso (IDROL_PERMISO, ROL_ID, PERMISO_ID) VALUES (10, 1, 10);

-- ============================================================
-- USUARIOS (password: password123)
-- ============================================================
INSERT INTO usuario (IDUSUARIO, USUARIO, PASSWORD, ROL_ID, ESTADO, CREATEDAT, UPDATEDAT)
VALUES (1, 'admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 1, 1, NOW(), NOW());

INSERT INTO usuario (IDUSUARIO, USUARIO, PASSWORD, ROL_ID, ESTADO, CREATEDAT, UPDATEDAT)
VALUES (2, 'cliente1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 2, 1, NOW(), NOW());

-- ============================================================
-- METODOS DE PAGO
-- ============================================================
INSERT INTO metodo_pago (IDMETODO_PAGO, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (1, 'Efectivo', 1, NOW(), NOW());
INSERT INTO metodo_pago (IDMETODO_PAGO, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (2, 'Tarjeta Credito/Debito', 1, NOW(), NOW());
INSERT INTO metodo_pago (IDMETODO_PAGO, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (3, 'Yape', 1, NOW(), NOW());
INSERT INTO metodo_pago (IDMETODO_PAGO, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (4, 'Plin', 1, NOW(), NOW());
INSERT INTO metodo_pago (IDMETODO_PAGO, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (5, 'Transferencia Bancaria', 1, NOW(), NOW());
INSERT INTO metodo_pago (IDMETODO_PAGO, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (6, 'Contraentrega', 1, NOW(), NOW());

-- ============================================================
-- CATEGORIAS
-- ============================================================
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (1, 'Ropa de Hombre', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (2, 'Ropa de Mujer', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (3, 'Ropa Deportiva', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (4, 'Accesorios', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (5, 'Calzado', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (6, 'Ropa Infantil', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (7, 'Jeans y Pantalones', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (8, 'Camisas y Blusas', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (9, 'Chaquetas y Abrigos', 1, NOW(), NOW());
INSERT INTO categoria (CODCATEGORIA, NOMBRE, ESTADO, CREATEDAT, UPDATEDAT) VALUES (10, 'Vestidos y Faldas', 1, NOW(), NOW());

-- ============================================================
-- PRODUCTOS (con ID explicito y CREATEDAT / UPDATEDAT)
-- ============================================================

-- Hombre (categoria 1)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (1, 1, 'Camisa Basica Manga Corta', 'Seikaze', 'Camisa basica de algodon para hombre, manga corta, corte regular', 25.00, 49.90, 59.90, 100, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (2, 1, 'Polo Algodon Pima', 'Seikaze', 'Polo de algodon pima peruano, suave y duradero', 30.00, 59.90, 69.90, 80, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (3, 1, 'Short Bermudas', 'Seikaze', 'Short tipo bermuda en tela gabardina, comodo y fresco', 35.00, 69.90, 79.90, 60, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (4, 1, 'Jean Slim Fit', 'Seikaze', 'Jean para hombre corte slim fit, elastizado', 45.00, 89.90, 99.90, 50, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (5, 1, 'Casaca Impermeable', 'Seikaze', 'Casaca impermeable con capucha, ideal para lluvia', 60.00, 119.90, 139.90, 40, 1, NULL, NOW(), NOW());

-- Mujer (categoria 2)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (6, 2, 'Blusa Elegante Manga Larga', 'Seikaze', 'Blusa elegante para mujer, manga larga, tela sedosa', 28.00, 55.90, 65.90, 90, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (7, 2, 'Vestido Floral Verano', 'Seikaze', 'Vestido estampado floral, ligero y fresco para verano', 40.00, 79.90, 89.90, 70, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (8, 2, 'Falda Plisada', 'Seikaze', 'Falda plisada larga, elastico en cintura, varios colores', 32.00, 64.90, 74.90, 65, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (9, 2, 'Cardigan Tejido', 'Seikaze', 'Cardigan tejido a mano, lana suave, ideal para invierno', 50.00, 99.90, 119.90, 35, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (10, 2, 'Leggins Deportivos', 'Seikaze', 'Leggins deportivos de compresion, tela breathable', 25.00, 49.90, 59.90, 100, 1, NULL, NOW(), NOW());

-- Deportiva (categoria 3)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (11, 3, 'Short Deportivo Hombre', 'Seikaze', 'Short deportivo con bolsillos, tela rapida', 22.00, 44.90, 54.90, 120, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (12, 3, 'Top Deportivo Mujer', 'Seikaze', 'Top deportivo de alto soporte para entrenamiento', 20.00, 39.90, 49.90, 85, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (13, 3, 'Buzo Polar', 'Seikaze', 'Buzo polar con cremallera, forro polar suave', 55.00, 109.90, 129.90, 45, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (14, 3, 'Malla Deportiva', 'Seikaze', 'Malla deportiva para running, con bolsillo para celular', 28.00, 55.90, 65.90, 75, 1, NULL, NOW(), NOW());

-- Accesorios (categoria 4)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (15, 4, 'Gorra Unisex', 'Seikaze', 'Gorra unisex con ajuste trasero, algodon', 10.00, 19.90, 29.90, 200, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (16, 4, 'Bufanda Tejida', 'Seikaze', 'Bufanda tejida a mano, lana acrilica', 15.00, 29.90, 39.90, 150, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (17, 4, 'Cinturon Cuero', 'Seikaze', 'Cinturon de cuero genuino, hebilla metalica', 18.00, 35.90, 45.90, 100, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (18, 4, 'Mochila Urbana', 'Seikaze', 'Mochila urbana 25L, impermeable, compartimento laptop', 35.00, 69.90, 89.90, 60, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (19, 4, 'Lentes de Sol', 'Seikaze', 'Lentes de sol polarizados, proteccion UV400', 12.00, 24.90, 34.90, 180, 1, NULL, NOW(), NOW());

-- Calzado (categoria 5)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (20, 5, 'Zapatillas Urbanas', 'Seikaze', 'Zapatillas urbanas unisex, suela amortiguada', 60.00, 119.90, 149.90, 50, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (21, 5, 'Zapatos Casuales', 'Seikaze', 'Zapatos casuales de cuero, suela flexible', 70.00, 139.90, 169.90, 40, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (22, 5, 'Sandalias Verano', 'Seikaze', 'Sandalias comodas para verano, suela antideslizante', 18.00, 35.90, 45.90, 120, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (23, 5, 'Botines Invierno', 'Seikaze', 'Botines de cuero forrados, suela antideslizante', 80.00, 159.90, 189.90, 30, 1, NULL, NOW(), NOW());

-- Infantil (categoria 6)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (24, 6, 'Set Baby 2 Piezas', 'Seikaze', 'Set de body y pantalon para bebe, algodon organico', 20.00, 39.90, 49.90, 80, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (25, 6, 'Polera Infantil', 'Seikaze', 'Polera infantil con estampado divertido', 15.00, 29.90, 39.90, 100, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (26, 6, 'Jean Infantil', 'Seikaze', 'Jean para nino/a, elastico en cintura', 25.00, 49.90, 59.90, 60, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (27, 6, 'Chompa Infantil', 'Seikaze', 'Chompa tejida para nino/a, lana suave', 30.00, 59.90, 69.90, 45, 1, NULL, NOW(), NOW());

-- Jeans y Pantalones (categoria 7)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (28, 7, 'Jean Clasico Hombre', 'Seikaze', 'Jean clasico de 5 bolsillos, corte regular', 42.00, 84.90, 94.90, 70, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (29, 7, 'Jean Skinny Mujer', 'Seikaze', 'Jean mujer corte skinny, tela elastizada', 40.00, 79.90, 89.90, 65, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (30, 7, 'Pantalon Gabardina Hombre', 'Seikaze', 'Pantalon de gabardina para hombre, corte recto', 38.00, 75.90, 85.90, 55, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (31, 7, 'Pantalon Palazzo Mujer', 'Seikaze', 'Pantalon palazzo mujer, tela fluida y comoda', 35.00, 69.90, 79.90, 50, 1, NULL, NOW(), NOW());

-- Camisas y Blusas (categoria 8)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (32, 8, 'Camisa Formal Manga Larga', 'Seikaze', 'Camisa formal para hombre, manga larga, cuello clasico', 35.00, 69.90, 79.90, 80, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (33, 8, 'Blusa Casual Mujer', 'Seikaze', 'Blusa casual con volantes, fresca y elegante', 26.00, 52.90, 62.90, 75, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (34, 8, 'Camisa Oxford Hombre', 'Seikaze', 'Camisa Oxford de algodon, ideal para oficina', 38.00, 75.90, 85.90, 60, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (35, 8, 'Blusa Seda Mujer', 'Seikaze', 'Blusa de seda suave, elegante para ocasiones especiales', 45.00, 89.90, 109.90, 40, 1, NULL, NOW(), NOW());

-- Chaquetas y Abrigos (categoria 9)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (36, 9, 'Chaqueta Jean Hombre', 'Seikaze', 'Chaqueta de jean clasica para hombre', 55.00, 109.90, 129.90, 40, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (37, 9, 'Abrigo Largo Mujer', 'Seikaze', 'Abrigo largo para mujer, tela paño, forro interior', 75.00, 149.90, 179.90, 25, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (38, 9, 'Chaleco Acolchado', 'Seikaze', 'Chaleco acolchado unisex, ideal para media estacion', 40.00, 79.90, 89.90, 55, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (39, 9, 'Parka Invierno', 'Seikaze', 'Parka de invierno con capucha, forro termico', 90.00, 179.90, 199.90, 20, 1, NULL, NOW(), NOW());

-- Vestidos y Faldas (categoria 10)
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (40, 10, 'Vestido Casual', 'Seikaze', 'Vestido casual corto, comodo para el dia a dia', 30.00, 59.90, 69.90, 60, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (41, 10, 'Vestido Noche Largo', 'Seikaze', 'Vestido de noche largo, tela brillante, elegante', 65.00, 129.90, 159.90, 30, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (42, 10, 'Falda Corta', 'Seikaze', 'Falda corta clasica, varios colores disponibles', 22.00, 44.90, 54.90, 80, 1, NULL, NOW(), NOW());
INSERT INTO producto (ID, CATEGORIA_ID, NOMBRE, MARCA, DESCRIPCION, COSTO, PRECIO_MIN, PRECIO_MAX, STOCK, ESTADO, IMAGEN, CREATEDAT, UPDATEDAT)
VALUES (43, 10, 'Vestido Midi Fit', 'Seikaze', 'Vestido midi ajustado, ideal para oficina', 38.00, 75.90, 85.90, 45, 1, NULL, NOW(), NOW());

-- ============================================================
-- VARIANTES
-- ============================================================
-- Producto 1: Camisa Basica
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (1, 1, 'S', 'Blanco', 49.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (2, 1, 'M', 'Blanco', 49.90, 25);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (3, 1, 'L', 'Blanco', 49.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (4, 1, 'XL', 'Blanco', 49.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (5, 1, 'S', 'Negro', 49.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (6, 1, 'M', 'Negro', 49.90, 25);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (7, 1, 'L', 'Negro', 49.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (8, 1, 'XL', 'Negro', 49.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (9, 1, 'S', 'Azul', 49.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (10, 1, 'M', 'Azul', 49.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (11, 1, 'L', 'Azul', 59.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (12, 1, 'XL', 'Azul', 59.90, 10);

-- Producto 2: Polo Algodon Pima
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (13, 2, 'S', 'Blanco', 59.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (14, 2, 'M', 'Blanco', 59.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (15, 2, 'L', 'Blanco', 59.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (16, 2, 'S', 'Negro', 59.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (17, 2, 'M', 'Negro', 59.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (18, 2, 'L', 'Negro', 69.90, 10);

-- Producto 4: Jean Slim Fit
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (19, 4, '28', 'Azul Oscuro', 89.90, 10);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (20, 4, '30', 'Azul Oscuro', 89.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (21, 4, '32', 'Azul Oscuro', 89.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (22, 4, '34', 'Azul Oscuro', 99.90, 10);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (23, 4, '30', 'Negro', 89.90, 10);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (24, 4, '32', 'Negro', 89.90, 12);

-- Producto 6: Blusa Elegante
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (25, 6, 'S', 'Blanco', 55.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (26, 6, 'M', 'Blanco', 55.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (27, 6, 'L', 'Blanco', 65.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (28, 6, 'S', 'Rosado', 55.90, 12);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (29, 6, 'M', 'Rosado', 55.90, 18);

-- Producto 7: Vestido Floral
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (30, 7, 'S', 'Multicolor', 79.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (31, 7, 'M', 'Multicolor', 79.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (32, 7, 'L', 'Multicolor', 89.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (33, 7, 'XL', 'Multicolor', 89.90, 10);

-- Producto 20: Zapatillas Urbanas
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (34, 20, '38', 'Blanco', 119.90, 10);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (35, 20, '39', 'Blanco', 119.90, 12);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (36, 20, '40', 'Blanco', 119.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (37, 20, '41', 'Blanco', 149.90, 10);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (38, 20, '42', 'Blanco', 149.90, 8);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (39, 20, '39', 'Negro', 119.90, 10);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (40, 20, '40', 'Negro', 119.90, 12);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (41, 20, '41', 'Negro', 149.90, 8);

-- Producto 40: Vestido Casual
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (42, 40, 'S', 'Negro', 59.90, 12);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (43, 40, 'M', 'Negro', 59.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (44, 40, 'L', 'Negro', 69.90, 10);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (45, 40, 'S', 'Rojo', 59.90, 10);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (46, 40, 'M', 'Rojo', 59.90, 12);

-- Producto 42: Falda Corta
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (47, 42, 'S', 'Negro', 44.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (48, 42, 'M', 'Negro', 44.90, 20);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (49, 42, 'L', 'Negro', 54.90, 15);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (50, 42, 'S', 'Azul Marino', 44.90, 12);
INSERT INTO producto_variante (IDVARIANTE, PRODUCTO_ID, TALLA, COLOR, PRECIO, STOCK) VALUES (51, 42, 'M', 'Azul Marino', 44.90, 15);

-- ============================================================
-- DESCUENTOS
-- ============================================================
INSERT INTO descuento (IDDESCUENTO, NOMBRE, DESCRIPCION, PORCENTAJE, FECHA_INICIO, FECHA_FIN, ESTADO, CREATEDAT, UPDATEDAT)
VALUES (1, 'Liquidacion Verano', 'Descuento por liquidacion de temporada verano', 30.00, '2026-01-01 00:00:00', '2026-03-31 23:59:59', 1, NOW(), NOW());

INSERT INTO descuento (IDDESCUENTO, NOMBRE, DESCRIPCION, PORCENTAJE, FECHA_INICIO, FECHA_FIN, ESTADO, CREATEDAT, UPDATEDAT)
VALUES (2, 'Cyber Monday', 'Descuento especial Cyber Monday', 50.00, '2026-05-25 00:00:00', '2026-06-01 23:59:59', 1, NOW(), NOW());

INSERT INTO descuento (IDDESCUENTO, NOMBRE, DESCRIPCION, PORCENTAJE, FECHA_INICIO, FECHA_FIN, ESTADO, CREATEDAT, UPDATEDAT)
VALUES (3, 'Descuento Nuevos Clientes', '10% de descuento en primera compra', 10.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59', 1, NOW(), NOW());

INSERT INTO descuento (IDDESCUENTO, NOMBRE, DESCRIPCION, PORCENTAJE, FECHA_INICIO, FECHA_FIN, ESTADO, CREATEDAT, UPDATEDAT)
VALUES (4, 'Oferta Relampago', 'Descuento flash por tiempo limitado', 40.00, '2026-06-01 00:00:00', '2026-06-07 23:59:59', 1, NOW(), NOW());
