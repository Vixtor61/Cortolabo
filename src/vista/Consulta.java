/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.EstudianteDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Movie;

/**
 *
 * @author Vixtor61
 */
public class Consulta extends JFrame {
    
    public JLabel  lblNombre, lblDirector, lblPais,lblClasificacion,lblAño, lblEn_proyeccion;

    public JTextField idMovie, nombre, director,pais,año;
    
    public JComboBox clasificacion;

    JCheckBox estado;
    
    
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Movie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
       
        container.add(lblNombre);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblAño);
        container.add(lblEn_proyeccion);
        container.add(lblClasificacion);
      
        container.add(nombre);
        container.add(director);
        container.add(clasificacion);
        container.add(pais);
        container.add(año);
        container.add(estado);
     
      
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(650, 650);
        eventos();
    }

    public final void agregarLabels() {
        
        lblNombre = new JLabel("Nombre:");
        
        lblPais = new JLabel("Pais:");
        lblEn_proyeccion = new JLabel("Estado:");
        lblAño = new JLabel("año:");
        lblDirector   = new JLabel("direcotor:");
        lblClasificacion  = new JLabel("Clasificacion:");
        lblAño.setBounds(340, 100, ANCHOC, ALTOC);

        lblNombre.setBounds(10, 30, ANCHOC, ALTOC);
        lblClasificacion.setBounds(340, 30, ANCHOC, ALTOC);
        lblPais.setBounds(10, 180, ANCHOC, ALTOC);
        lblEn_proyeccion.setBounds(340, 180, ANCHOC, ALTOC);
        lblDirector.setBounds(10, 100, ANCHOC, ALTOC);
        
    }

    public final void formulario() {
        nombre = new JTextField();
        clasificacion = new JComboBox();
        director= new JTextField();
        pais = new JTextField();
        año = new JTextField();
        
      
        resultados = new JTable();
        
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        clasificacion.addItem("G");
        clasificacion.addItem("PG");
        clasificacion.addItem("14A");
       clasificacion.addItem("18A");
       clasificacion.addItem("R");
       clasificacion.addItem("A");
        
        estado = new JCheckBox();

        nombre.setBounds(140, 10, ANCHOC, ALTOC);
        director.setBounds(140, 100, ANCHOC, ALTOC);
        clasificacion.setBounds(500, 30, 100, ALTOC);
        pais.setBounds(140, 180, 100, ALTOC);
        año.setBounds(500, 100, 100, ALTOC);
        estado.setBounds(140, 100, ANCHOC, ALTOC);
        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 300, ANCHOC, ALTOC);
        actualizar.setBounds(150, 300, ANCHOC, ALTOC);
        eliminar.setBounds(300, 300, ANCHOC, ALTOC);
        limpiar.setBounds(450, 300, ANCHOC, ALTOC);
        resultados = new JTable();
        estado.setBounds(400, 200, ANCHOC, ALTOC);
        
        resultados = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; 
            }
        };
        table.setBounds(10, 350, 600, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    case 6:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("nombre");
        tm.addColumn("director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificacion");
        tm.addColumn("año");
        tm.addColumn("Estado");

        EstudianteDao ed = new EstudianteDao();
        ArrayList<Movie> filtros = ed.readAll();

        for (Movie estu : filtros) {
            tm.addRow(new Object[]{estu.getNombre(), estu.getDiretor(), estu.getPais(), estu.getClasificacion(), estu.getAño(),estu.isEn_proyeccion()});
        }

        resultados.setModel(tm);
    }

    /**
     * ed = EstudianteDao objeto
     */
    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteDao ed = new EstudianteDao();
                Movie estu = new Movie( nombre.getText(), director.getText(),pais.getText(), clasificacion.getSelectedItem().toString(),Integer.parseInt(año.getText()), estado.isSelected());

                if (estado.isSelected()) {
                    estu.setEn_proyeccion(true);
                }

                if (ed.create(estu)) {
                    JOptionPane.showMessageDialog(null, "Movie registrado con existo");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el Movie");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteDao ed = new EstudianteDao();
                Movie estu = new Movie(nombre.getText(), director.getText(),pais.getText(), clasificacion.getSelectedItem().toString(),Integer.parseInt(año.getText()), true);

                if (estado.isSelected()) {
                    estu.setEn_proyeccion(true);
                }

                if (ed.update(estu)) {//cambiar en metodo de Object Key a Generic g.
                    JOptionPane.showMessageDialog(null, "movie modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el estudiante");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteDao ed = new EstudianteDao();
                if (ed.delete(nombre.getText())) {
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el estudiante");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteDao ed = new EstudianteDao();
                Movie estu = ed.read(nombre.getText());
                if (estu == null) {
                    JOptionPane.showMessageDialog(null, "El estudiante buscado no se ha encontrado");
                } else {
                    
                    nombre.setText(estu.getNombre());
                    director.setText(estu.getDiretor());
                    clasificacion.setSelectedItem(estu.getClasificacion());
                   
                    
                    pais.setText(estu.getPais());

                    if (estu.isEn_proyeccion()) {
                        estado.setSelected(true);
                    } else {
                        estado.setSelected(false);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        resultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    nombre.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
                    clasificacion.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 3).toString());
                    director.setText(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
//                    edad.setText(resultados.getValueAt(resultados.getSelectedRow(), 3).toString()); 
                    pais.setText(resultados.getValueAt(resultados.getSelectedRow(), 2).toString()); 
                    if (resultados.getValueAt(resultados.getSelectedRow(), 4).toString() == "false") {
                        estado.setSelected(true);
                    } else {
                        estado.setSelected(false);
                    }
                }
            }
        });
    }

    public void limpiarCampos() {
        nombre.setText("");
        clasificacion.setSelectedItem("G");
        nombre.setText("");
        pais.setText("");
        año.setText("");
        director.setText("");
        estado.setSelected(false);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}
