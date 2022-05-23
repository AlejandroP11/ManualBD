
package manualbd;

import java.sql.Statement;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Pereiro G
 */
public class Conexion {
    
    //metodo que nos permite conectarnos a la base de datos
    public static Connection conectar(){
        Connection conn = null; //creamos un objeto de tipo Connection
        
        try {
            String url = "jdbc:sqlite:C:\\Users\\34653\\OneDrive\\Escritorio\\Usuarios.db.db"; //url de la base de datos
            conn = DriverManager.getConnection(url); //con el DriverManager nos conectamos a la base de datos con el url
            
        } catch (SQLException ex) { //tratamos cualquier excepcion de tipo SQL que podamos tener
            System.out.println("Error" + ex.getMessage()); 
            
        } 
        return conn; //devolvemos un objeto de tipo Connection
    }
    
    //metodo que nos permite desconectarnos de la base de datos
    public static void desconectar(){
        Connection conn = conectar(); //usamos el metodo conectar y creamos un objeto de tipo Connection
        
            try {
                if(conn != null) //si la conexion es distinta de null 
                conn.close(); //la cerramos

            } catch (SQLException ex) { //tratamos cualquier excepcion de tipo SQL que podamos tener
                System.out.println("Error " + ex.getMessage());
        }
    }
    
    //metodo que nos permite insertar un nuevo usuario en la base de datos
    public static void insertar(Usuario us){    
        try {
            Connection conn = conectar(); //usamos el metodo conectar y creamos un objeto de tipo Connection
            //creamos nuestra consulta, en este caso un Insert
            String sql = "INSERT INTO Usuarios(dni, nombre, apellidos, edad) VALUES(?,?,?,?)"; 
            var pstmt = conn.prepareStatement(sql); //creamos un objeto de tipo var que nos permite preparar 
            //los datos que vamos a ingresar con la consulta
            pstmt.setString(1, us.getDni()); //añadimos el dni
            pstmt.setString(2, us.getNombre()); //añadimos el nombre
            pstmt.setString(3, us.getApellidos()); //añadimos los apellidos
            pstmt.setInt(4, us.getEdad()); //añadimos la edad
            pstmt.executeUpdate(); //ejecutamos la consulta
            desconectar(); //usamos la clase desconectar para cerrar la base de datos
            
        } catch (SQLException ex) { //tratamos cualquier excepcion de tipo SQL que podamos tener
            System.out.println("Error " + ex.getMessage());
        }
    }
    
    //metodo que nos permite eliminar un usuario de la base de datos, buscandolo por su dni
    public static void eliminar(String dni){
        try {
            Connection conn = conectar(); //usamos el metodo conectar y creamos un objeto de tipo Connection
            Statement stmt = conn.createStatement(); //creamos un objeto de tipo Statement que ejecutara la consulta
            String sql = "DELETE FROM Usuarios WHERE DNI='"+dni+"'"; //creamos nuestra consulta, en este caso un Delete, 
            //comparando al usuario con el dni ingresado
            stmt.executeUpdate(sql); //ejecutamos la consulta
            desconectar(); //usamos la clase desconectar para cerrar la base de datos
            
        } catch (SQLException ex) { //tratamos cualquier excepcion de tipo SQL que podamos tener
            System.out.println("Error "+ex.getMessage());
        }
    }
    
    //metodo que nos permite modificar los datos de un usario buscandolo por su dni
    public static void modificar(Usuario us){
        try {
            Connection conn = conectar(); //usamos el metodo conectar y creamos un objeto de tipo Connection
            Statement stmt = conn.createStatement(); //creamos un objeto de tipo Statement que ejecutara la consulta
            String sql = "UPDATE Usuarios SET dni='"+us.getDni()+"',nombre='"+us.getNombre()+"',apellidos='"+
                    us.getApellidos()+"',edad='"+us.getEdad()+"' WHERE dni='"+us.getDni()+"'";
            //creamos nuestra consulta, en este caso un Update, comparando al usuario con el dni ingresado
            stmt.executeUpdate(sql); //ejecutamos nuestra consulta
            desconectar(); //usamos la clase desconectar para cerrar la base de datos
            
        } catch (SQLException ex) { //tratamos cualquier excepcion de tipo SQL que podamos tener
            System.out.println("Error "+ex.getMessage());
        }
    }
    
    //metodo que nos permite crear la tabla que visualizaremos en la pantalla
    public static DefaultTableModel leer(){
        DefaultTableModel modelo = new DefaultTableModel();//creamos un modelo de tabla default
        
        try {
            Connection conn = conectar(); //usamos el metodo conectar y creamos un objeto de tipo Connection
            Statement stmt = conn.createStatement(); //creamos un objeto de tipo Statement que ejecutara una consulta
            String sql = "SELECT * FROM Usuarios"; //creamos una consulta para seleccionar la tabla de la base de datos
            var rs = stmt.executeQuery(sql); //creamos un objeto de tipo var que recibira la ejecucion de la consulta
            var rsmd = rs.getMetaData(); //creamos un nuevo objeto de tipo var que recibira los meta datos de la tabla seleccionada
            int canCol = rsmd.getColumnCount(); //instaciamos una variable int que contendra el numero de columnas contadas
            for(int i = 1; i <= canCol; i++) //mientras que i sea menor o igual que el numero de columnas
                modelo.addColumn(rsmd.getColumnLabel(i)); //añadiremos una columnda, con el nombre de la columna de la tabla de la 
                //base de datos, a nuestro modelo de tabla
            while(rs.next()){ //mientras la tabla tenga otra fila
                Object[] fila = new Object[canCol]; //crearemos una Array de tipo objeto y de tamaño igual al numero de columnas
                for(int i = 0; i < canCol; i++) //mientras que i sea menos que el numero de columnas
                    fila[i] = rs.getObject(i+1); //la array recibira el objeto de que se encuentre en la fila i+1
                modelo.addRow(fila); //se añadira la array a las filas de nuestra tabla
            }
            desconectar(); //usamos la clase desconectar para cerrar la base de datos
            
        } catch (SQLException ex) { //tratamos cualquier excepcion de tipo SQL que podamos tener
            System.out.println("Error " + ex.getMessage());
            
        }
            return modelo;
    }
}
