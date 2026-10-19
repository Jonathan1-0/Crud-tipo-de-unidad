package Polizas;

import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class T_Unidad extends JFrame implements ActionListener {

    JLabel Titu, idf, nombref, categoriaf, capacidadf, combusf;
    JTextField idc, nomc, catc, capc, comc;
    JButton consultar, agregar, actualizar, eliminar;
    DefaultTableModel Tablas;
    JTable tabla;
    JScrollPane scroTabla;

    T_Unidad() {
        this.setBounds(150, 50, 800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        this.Titu = new JLabel();
        this.Titu.setBounds(300, 20, 250, 30);
        this.Titu.setText("Tipo de Unidad");
        this.add(Titu);

        this.nombref = new JLabel("Nombre");
        this.nombref.setBounds(380, 60, 90, 25);
        this.add(nombref);

        this.idf = new JLabel("ID");
        this.idf.setBounds(50, 60, 90, 25);
        this.add(idf);

        this.categoriaf = new JLabel("Categoría");
        this.categoriaf.setBounds(50, 100, 90, 25);
        this.add(categoriaf);

        this.capacidadf = new JLabel("Capacidad");
        this.capacidadf.setBounds(380, 100, 90, 25);
        this.add(capacidadf);

        this.combusf = new JLabel("Combustible");
        this.combusf.setBounds(50, 140, 90, 25);
        this.add(combusf);

        this.nomc = new JTextField();
        this.nomc.setBounds(480, 60, 220, 25);
        this.add(nomc);

        this.idc = new JTextField();
        this.idc.setBounds(140, 60, 180, 25);
        this.add(idc);

        this.catc = new JTextField();
        this.catc.setBounds(140, 100, 180, 25);
        this.add(catc);

        this.capc = new JTextField();
        this.capc.setBounds(480, 100, 220, 25);
        this.add(capc);

        this.comc = new JTextField();
        this.comc.setBounds(140, 140, 180, 25);
        this.add(comc);

        this.consultar = new JButton("Consultar");
        this.consultar.setBounds(110, 185, 120, 30);
        this.add(consultar);

        this.agregar = new JButton("Agregar");
        this.agregar.setBounds(250, 185, 120, 30);
        this.add(agregar);

        this.actualizar = new JButton("Actualizar");
        this.actualizar.setBounds(390, 185, 120, 30);
        this.add(actualizar);

        this.eliminar = new JButton("Eliminar");
        this.eliminar.setBounds(530, 185, 120, 30);
        this.add(eliminar);

        this.agregar.addActionListener(this);
        this.consultar.addActionListener(this);
        this.actualizar.addActionListener(this);
        this.eliminar.addActionListener(this);

        Tablas = new DefaultTableModel();
        Tablas.addColumn("ID");
        Tablas.addColumn("Nombre");
        Tablas.addColumn("Categoría");
        Tablas.addColumn("Capacidad");
        Tablas.addColumn("Combustible");

        tabla = new JTable(Tablas);
        scroTabla = new JScrollPane(tabla);
        scroTabla.setBounds(50, 235, 680, 280);
        this.add(scroTabla);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        T_Unidad unidad = new T_Unidad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.consultar) {
            this.consultar();
        }

        if (e.getSource() == this.agregar) {
            this.agregar();
        }

        if (e.getSource() == this.eliminar) {
            this.eliminar();
        }
        if (e.getSource() == this.actualizar) {
    this.actualizar();
        }

    }
    
    Unidades objeunidad = new Unidades();

    public void agregar() {
        try {
            String nombre = this.nomc.getText().trim();
            String categoria = this.catc.getText().trim();
            int capacidad = Integer.parseInt(this.capc.getText().trim());
            String combustible = this.comc.getText().trim();

            objeunidad.agregar(nombre, categoria, capacidad, combustible);
            this.consultar();

        } catch (NumberFormatException ex) {
            System.out.println("Error: La capacidad debe ser un número entero.");
        }
    }

    public void eliminar() {
        String idTexto = this.idc.getText().trim();
        String nombreTexto = this.nomc.getText().trim();

        objeunidad.eliminar(idTexto, nombreTexto);
    }

    public void consultar() {
        objeunidad.consultar(this.Tablas);
        TamaTabla();
    }

    public void actualizar() {
        String id = this.idc.getText().trim();
        String nombre = this.nomc.getText().trim();
        String categoria = this.catc.getText().trim();
        String capacidad = this.capc.getText().trim();
        String combustible = this.comc.getText().trim();

        objeunidad.actualizar(id, nombre, categoria, capacidad, combustible);
    }

    public void TamaTabla() {
        int filas = Tablas.getRowCount();
        int altoFila = tabla.getRowHeight();
        int altoCabecera = tabla.getTableHeader().getPreferredSize().height;

        int altoCalculado = altoCabecera + (filas * altoFila) + 5;

        int altoMinimo = 60;
        int altoMaximo = 280;

        int altoFinal = Math.max(altoMinimo, Math.min(altoCalculado, altoMaximo));

        scroTabla.setBounds(50, 235, 680, altoFinal);

        this.revalidate();
        this.repaint();

    }

}
