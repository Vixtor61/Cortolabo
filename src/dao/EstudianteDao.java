package dao;

import conexion.Conexion;
import interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Movie;

/**
 *Nombre de la base de datos: inscripciones
 * Nombre de la tabla: 
 * @author Vixtor61 <00198117@iuca.edu.sv>
 */
public class EstudianteDao implements Metodos<Movie>{
    private static final String SQL_INSERT = "INSERT INTO movie ( nombre, director,pais, clasificacion,anio,en_proyeccion) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE movie SET en_proyeccion = ? WHERE nombre = ?";
    private static final String SQL_DELETE = "DELETE FROM movie WHERE nombre = ?";
    private static final String SQL_READ = "SELECT * FROM movie WHERE nombre = ?";
    private static final String SQL_READALL = "SELECT * FROM movie";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Movie g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);

            ps.setString(1, g.getNombre());
            ps.setString(2, g.getDiretor());
            ps.setString(3, g.getPais());
            ps.setString(4, g.getClasificacion());
            ps.setInt(5, g.getAño());
            ps.setBoolean(6, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.cerrarConexion();
        }
        return false;    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Movie c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getIdMovie());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setBoolean(1, c.isEn_proyeccion());
            ps.setString(2, c.getNombre());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Movie read(Object key) {
        Movie estu = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                estu = new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getInt(6),rs.getBoolean(7));//constructor
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return estu;
    }

    @Override
    public ArrayList<Movie> readAll() {
        ArrayList<Movie> all = new ArrayList();
        Statement s;
        ResultSet rs;

        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);

            while (rs.next()) {
                all.add(new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getInt(6),rs.getBoolean(7)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

    
    
}
