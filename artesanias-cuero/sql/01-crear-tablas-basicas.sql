-- =====================================================
-- SCRIPT: 01-crear-tablas-basicas.sql
-- BASE DE DATOS: artesanias_cuero
-- PROYECTO: Artesanías en Cuero
-- FECHA: 2026-03-04
-- =====================================================

-- ============================================
-- TABLA 1: colors (catálogo de colores)
-- ============================================
CREATE TABLE colors (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  hex_code VARCHAR(7),
  active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- TABLA 2: materials (materiales -cuero, hilo, etc.)
-- ============================================
CREATE TABLE materials (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE,
  category VARCHAR(50) NOT NULL,
  base_unit VARCHAR(20) NOT NULL,
  stock_min DECIMAL(14,4) DEFAULT 0,
  active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- TABLA 3: material_lots (lotes de inventario)
-- ============================================
CREATE TABLE material_lots (
  id SERIAL PRIMARY KEY,
  material_id INTEGER NOT NULL REFERENCES materials(id),
  color_id INTEGER REFERENCES colors(id),
  lot_code VARCHAR(50) NOT NULL,
  qty_initial DECIMAL(14,4) NOT NULL CHECK (qty_initial >= 0),
  qty_on_hand DECIMAL(14,4) NOT NULL CHECK (qty_on_hand >= 0),
  unit_cost DECIMAL(14,4) DEFAULT 0 CHECK (unit_cost >= 0),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE(material_id, lot_code)
);

-- ============================================
-- INSERTAR COLORES
-- ============================================
INSERT INTO colors (name, hex_code) VALUES
  ('Negro', '#000000'),
  ('Marrón', '#8B4513'),
  ('Natural', '#D2B48C'),
  ('Rojo', '#8B0000');

-- ============================================
-- INSERTAR MATERIALES
-- ============================================
INSERT INTO materials (name, category, base_unit, stock_min) VALUES
  ('Cuero vacuno', 'Materia prima', 'm2', 5.00),
  ('Hilo encerado', 'Insumos', 'metros', 100.00),
  ('Hebilla metal', 'Accesorios', 'unidad', 20.00),
  ('Forro tela', 'Insumos', 'm2', 10.00);

-- ============================================
-- INSERTAR LOTES DE CUERO
-- ============================================
INSERT INTO material_lots (material_id, color_id, lot_code, qty_initial, qty_on_hand, unit_cost) VALUES
  (1, 1, 'CVN-2026-001', 3.00, 3.00, 45.00),
  (1, 2, 'CVN-2026-002', 3.00, 2.50, 45.00),
  (1, 3, 'CVN-2026-003', 3.00, 3.00, 42.00),
  (2, NULL, 'HIL-2026-001', 500.00, 500.00, 0.50),
  (3, NULL, 'HEB-2026-001', 50.00, 50.00, 8.00);

-- ============================================
-- CONSULTAS DE VERIFICACIÓN
-- ============================================
-- SELECT * FROM colors;
-- SELECT * FROM materials;
-- SELECT * FROM material_lots;