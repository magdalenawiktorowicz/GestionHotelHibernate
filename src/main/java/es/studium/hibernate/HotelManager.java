package es.studium.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HotelManager {
	// método para abrir la conexión a la BD según la configuración Hibernate
	private static Session getSession() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
	
	// método para crear un cliente en la BD
	public static void crear(Cliente cliente) {
		// obtener la conexión a la BD
		Session sessionObj = getSession();
		// empezar la transacción
		Transaction tx = sessionObj.beginTransaction();
		// hacemos persistente el objeto Cliente (el que pasamos como parámetro)
		sessionObj.persist(cliente);
		// confirmar la operación
		tx.commit();
		// cerrar el objeto de Session	
		sessionObj.close();
		System.out.println("Cliente " + cliente.getNombre() + " " + cliente.getApellidos() + " (DNI: " + cliente.getDni()
		+ ")" + " se ha insertado correctamente en la tabla 'cliente' de la base de datos 'hotel'.");

	}
	
	// método para mostrar un listado de todos los clientes de la tabla 'cliente'
	public static List<Cliente> leer() {
		// sentencia HQL de consulta
		String consulta = "FROM " + Cliente.class.getName();
		// obtener la conexión a la BD
		Session sessionObj = getSession();
		// ejecutar la sentencia HQL con el método createQuery y enlistar los registros obtenidos
		List<Cliente> listado = sessionObj.createQuery(consulta, Cliente.class).list();
		// crear un iterador para poder iterar los registros de la lista
		Iterator<Cliente> iteradorResultado = listado.iterator();
		// si hay registros en el listado
		while (iteradorResultado.hasNext()) {
			// asignamos cada registro a un objeto Cliente
			Cliente cliente = iteradorResultado.next();
			// mostramos la información de cada cliente
			System.out.println("ID: " + cliente.getIdCliente() + "\t NOMBRE: " + cliente.getNombre()
			+ " \t APELLIDOS: " + cliente.getApellidos() + "\t EMAIL: " + cliente.getEmail() + "\t DNI: "
					+ cliente.getDni() + " \t CLAVE: " + cliente.getClave());
		}
		// cerrar el objeto de Session
		sessionObj.close();
		return listado;
	}
	
	// método para actualizar un cliente en la BD por su id
	public static void actualizar(int idCliente, String campo, String nuevoValor) {
		// abrir la conexión a la BD
		Session sessionObj = getSession();
		// empezar la transacción
		Transaction transObj = sessionObj.beginTransaction();
		// obtener el cliente por su id
		Cliente cliente = sessionObj.get(Cliente.class, idCliente);
		if (cliente != null) {
            // modificar el valor del campo con un setter correspondiente al campo pasado
            switch (campo.toLowerCase()) {
                case "nombre":
                    cliente.setNombre(nuevoValor);
                    break;
                case "apellidos":
                    cliente.setApellidos(nuevoValor);
                    break;
                case "email":
                    cliente.setEmail(nuevoValor);
                    break;
                case "dni":
                    cliente.setDni(nuevoValor);
                    break;
                case "clave":
                    cliente.setClave(nuevoValor);
                    break;
                default:
                    System.out.println("Campo no válido: " + campo);
            }
		}

		try {			
			// actualizar el objeto
			sessionObj.merge(cliente);
			// confirmar la modificación
			transObj.commit();
			System.out.println("El cliente con ID: " + idCliente + " se ha actualizado correctamente.");
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error.");
		} finally {
			// cerrar el objeto de Session
			sessionObj.close();
		}
	}

	// método para eliminar un cliente de la BD por su id
	public static void eliminar(int idCliente) {
		// abrir la conexión a la BD
		Session sessionObj = getSession();
		// empezar la transacción
		Transaction transObj = sessionObj.beginTransaction();
		// obtener el cliente por su id
		Cliente cliente = sessionObj.get(Cliente.class, idCliente);
		
		try {			
			// eliminar el objeto
			sessionObj.remove(cliente);
			// confirmar la operación
			transObj.commit();
			System.out.println("El cliente con ID: " + idCliente + " se ha eliminado correctamente.");
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error.");
		} finally {
			// cerrar el objeto de Session
			sessionObj.close();
		}
	}
}
