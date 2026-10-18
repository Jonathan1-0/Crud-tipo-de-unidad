package Polizas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Unidades{

    private Conexion conexion = new Conexion();

    public void agregar(String nombre, String categoria, int capacidad, String combustible) {
        try {

            Connection cnx = conexion.getConexion();

            PreparedStatement ps = cnx.prepareStatement("insert into tipo_unidad (nombre, categoria, capacidad, combustible) values (?, ?, ?, ?)");

            ps.setString(1, nombre);
            ps.setString(2, categoria);
            ps.setInt(3, capacidad);
            ps.setString(4, combustible);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Agregado");
            }

            cnx.close();

        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

    public void eliminar(String idTexto, String nombreTexto) {
        try {
            Connection cnx = conexion.getConexion();
            PreparedStatement ps = null;

            if (!idTexto.isEmpty()) {

                ps = cnx.prepareStatement("delete from tipo_unidad where id = ?");
                ps.setInt(1, Integer.parseInt(idTexto));
            } else if (!nombreTexto.isEmpty()) {

                ps = cnx.prepareStatement("delete from tipo_unidad where nombre = ?");
                ps.setString(1, nombreTexto);
            }

            if (ps != null) {
                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("eliminado");
                }
            }
            cnx.close();
        } catch (SQLException ex) {
        }
    }

    public void consultar(DefaultTableModel tabla) {
        try {
            Connection cnx = conexion.getConexion();
            PreparedStatement ps = cnx.prepareStatement("select * from tipo_unidad");
            ResultSet rs = ps.executeQuery();

            tabla.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                int capacidad = rs.getInt("capacidad");
                String combustible = rs.getString("combustible");

                tabla.addRow(new Object[]{id, nombre, categoria, capacidad, combustible});
            }

            cnx.close();

        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }
    
   public void actualizar(String id, String nombre, String categoria, String capacidad, String combustible) {
    if (id.isEmpty()) {
        return;
    }

    try {
        Connection cnx = conexion.getConexion();

        PreparedStatement psBuscar = cnx.prepareStatement("select * from tipo_unidad where id = ?");
        psBuscar.setInt(1, Integer.parseInt(id));
        ResultSet rs = psBuscar.executeQuery();

        if (!rs.next()) {
            cnx.close();
            return;
        }

        String nombreActual = rs.getString("nombre");
        String categoriaActual = rs.getString("categoria");
        int capacidadActual = rs.getInt("capacidad");
        String combustibleActual = rs.getString("combustible");

        String nuevoNombre = nombre.isEmpty() ? nombreActual : nombre;
        String nuevaCategoria = categoria.isEmpty() ? categoriaActual : categoria;
        String nuevoCombustible = combustible.isEmpty() ? combustibleActual : combustible;

        int nuevaCapacidad = capacidadActual;
        if (!capacidad.isEmpty()) {
            nuevaCapacidad = Integer.parseInt(capacidad);
        }

        String sql = "update tipo_unidad set nombre = ?, categoria = ?, capacidad = ?, combustible = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setString(1, nuevoNombre);
        ps.setString(2, nuevaCategoria);
        ps.setInt(3, nuevaCapacidad);
        ps.setString(4, nuevoCombustible);
        ps.setInt(5, Integer.parseInt(id));

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Actualizado");
        }

        cnx.close();

    } catch (SQLException ex) {
        System.out.println("Error" + ex.getMessage());
    } catch (NumberFormatException ex) {
        System.out.println("Error en el id porque sos un capo");
    }
}
}
