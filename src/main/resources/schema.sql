CREATE DATABASE FellasLibrary;
USE FellasLibrary;

create table administrador(
idadministrador int(10)not null primary key,
nombre varchar(30)not null,
contraseña varchar (15)not null);

CREATE TABLE Libros (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    año_publicacion YEAR NOT NULL,
    categoria VARCHAR(100),
    isbn VARCHAR(13) UNIQUE,
    formato ENUM('Fisico', 'Virtual', 'Audiolibro') NOT NULL,
    precio_flcoin DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    ubicacion VARCHAR(255)
);

CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    saldo_flcoin DECIMAL(10, 2) DEFAULT 0
);

CREATE TABLE Transacciones (
    id_transaccion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_libro INT,
    tipo ENUM('Compra', 'Alquiler') NOT NULL,
    fecha_transaccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cantidad_flcoin DECIMAL(10, 2) NOT NULL,
    fecha_devolucion DATE,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_libro) REFERENCES Libros(id_libro)
);

CREATE TABLE Historial_Saldo (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    cambio_saldo DECIMAL(10, 2) NOT NULL,
    tipo ENUM('Recarga', 'Compra', 'Alquiler') NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

CREATE TABLE Librerias (
    id_libreria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_libreria VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    latitud DECIMAL(9, 6),
    longitud DECIMAL(9, 6)
);

CREATE TABLE Solicitudes_Usuarios (
    id_solicitud INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    tipo ENUM('Reporte', 'Solicitud') NOT NULL,
    descripcion TEXT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('Pendiente', 'En Proceso', 'Resuelto') DEFAULT 'Pendiente',
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

CREATE TABLE Preferencias_Usuario (
    id_preferencia INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    tema ENUM('Claro', 'Oscuro') DEFAULT 'Claro',
    accesibilidad ENUM('Ninguna', 'Contraste Alto', 'Lector de Pantalla') DEFAULT 'Ninguna',
    notificaciones BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);






