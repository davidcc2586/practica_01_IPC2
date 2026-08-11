CREATE DATABASE JavaBeansCafe;
USE JavaBeansCafe;
CREATE TABLE Empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    DPI VARCHAR(13) NOT NULL UNIQUE ,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    rol ENUM ('mesero', 'cocinero', 'barista', 'administrador') NOT NULL ,
    jornadaLaboral ENUM ('matutina', 'vespertina', 'nocturna') NOT NULL,
    salario DECIMAL(8,2) NOT NULL,
    fechaContratacion DATE NOT NULL,
    estado ENUM ('activo', 'inactivo') DEFAULT 'activo',
    direcionImagen VARCHAR(50) DEFAULT '/Imagenes/empleado.png'
);
CREATE TABLE Mesa(
    id_mesa INT AUTO_INCREMENT PRIMARY KEY,
    capacidad INT NOT NULL,
    estadoMesa ENUM ('libre', 'ocupada') DEFAULT 'libre'
);
CREATE TABLE  Inventario(
    id_insumo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    unidadMedida VARCHAR(50) NOT NULL,
    cantidadStock INT DEFAULT 0,
    stockMinimo INT,
    costoInsumo DECIMAL(10,2),
    direccionImagen VARCHAR(100) NOT NULL
);
CREATE TABLE Menu(
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombreProducto VARCHAR(100) NOT NULL,
    categoria ENUM ('bebida caliente', 'bebida fria', 'postre', 'comida') NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    direccionImagen VARCHAR(100) NOT NULL
);
CREATE TABLE Pedido(
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    idMesero INT NOT NULL,
    idMesa INT NOT NULL,
    fecha DATE NOT NULL,
    horaOcuapacion TIME not null,
    horaLiberacion TIME,
    estadoCuenta ENUM ('abierta', 'pagada') DEFAULT 'abierta',
    totalPagar DECIMAL(10,2),
    propina INT,
    FOREIGN KEY (idMesero) REFERENCES Empleado(id_empleado),
    FOREIGN KEY (idMesa) REFERENCES Mesa(id_mesa)
);
CREATE TABLE DetallePedido(
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    idPedido int NOT NULL,
    idProducto INT NOT NULL,
    cantidad int NOT NULL,
    precioUnitario DECIMAl(10,2) not null ,
    FOREIGN KEY (idProducto) REFERENCES Menu(id_producto),
    FOREIGN KEY (idPedido) REFERENCES Pedido(id_pedido)
);
CREATE TABLE ProductoInsumo(
    id_insumo INT,
    id_producto int ,
    cantidad INT,
    primary key (id_insumo, id_producto),
    foreign key (id_insumo) REFERENCES Inventario(id_insumo),
    FOREIGN KEY (id_producto) REFERENCES Menu(id_producto)
);
CREATE TABLE Nomina(
    id_insumo INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT NOT NULL,
    estado ENUM('pendiente', 'pagado') DEFAULT 'pendiente',
    monto DOUBLE(10,2) NOT NULL,
    fechaPago DATE,
    tipo ENUM('quincena', 'fin de mes') not null
);
CREATE TABLE Transacciones(
    id_transaccion INT AUTO_INCREMENT,
    tipo ENUM('ingreso', 'egreso') NOT NULL,
    motivo VARCHAR(150) NOT NULL,
    monto DOUBLE NOT NULL
)
