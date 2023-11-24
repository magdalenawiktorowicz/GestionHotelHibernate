package es.studium.hibernate;

public class TestHotel {
	public static void main(String[] args) {
		// crear dos objetos de tipo Cliente
		Cliente c1 = new Cliente("Noelia", "Isnard Díaz", "noeliaisnard@email.com", "12345678Z", "password123");
		Cliente c2 = new Cliente("David", "Ruíz Rodríguez", "davidrruiz@email.com", "23456789W", "123.password");
		// crear los mismos clientes en la base de datos a través del método crear al que pasamos como parámetro un objeto de cliente
		HotelManager.crear(c1);
		HotelManager.crear(c2);
		// consultar la tabla 'clientes' de la base de datos
		HotelManager.leer();
		// actualizar el campo de clave del cliente c1 utilizando el método actualizar al que pasamos 3 parámetros:
		// el id del cliente, el campo que queremos modificar y el nuevo valor del campo
		HotelManager.actualizar(41, "clave", "Secret.Password.098");
		// actualizar el campo de dni del cliente c2
		HotelManager.actualizar(42, "dni", "98765432R");
		// consultar el contenido de la tabla
		HotelManager.leer();
		// eliminar el cliente c2 utilizando el método eliminar al que pasamos como parámetro el id
		HotelManager.eliminar(42);
		// intentar eliminar un cliente con ID que no existe en la tabla
		HotelManager.eliminar(104);
		// consultar el contenido de la tabla
		HotelManager.leer();
	}
}
