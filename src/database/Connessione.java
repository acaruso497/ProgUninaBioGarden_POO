package database;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {
	public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/ProgettoGardenUnina";
        String user = "postgres";
        String password = "Admin"; // cambia se serve

        return DriverManager.getConnection(url, user, password);
    }
}
*/



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//______________singleton thread-safe______________________

public final class Connessione {

	  private static final String URL = "jdbc:postgresql://localhost:5432/ProgettoGardenUnina";
	  private static final String USER = "postgres";
	  private static final String PASSWORD = "Admin";

	  private static volatile Connessione instance;
	  private final int CHANNEL_ID;

	  private Connessione() {
	    try { Class.forName("org.postgresql.Driver"); }
	    catch (ClassNotFoundException e) { throw new IllegalStateException("Driver PostgreSQL non trovato", e); }
	    this.CHANNEL_ID = System.identityHashCode(this);
	    System.out.println("[Canale " + CHANNEL_ID + "] Singleton pronto");
	  }

	  public static Connessione getInstance() {
	    Connessione r = instance;
	    if (r == null) {
	      synchronized (Connessione.class) {
	        r = instance;
	        if (r == null) instance = r = new Connessione();
	      }
	    }
	    return r;
	  }

	  public Connection open() throws SQLException {
	    try {
	      Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
	      System.out.println("[Canale " + CHANNEL_ID + "] OK → " + URL + " (user=" + USER + ")");
	      return c;
	    } catch (SQLException e) {
	      System.err.println("[Canale " + CHANNEL_ID + "] FALLITA → " + URL + " (user=" + USER + "): " + e.getMessage());
	      throw e;
	    }
	  }

	  public static Connection getConnection() throws SQLException {
	    return getInstance().open();
	  }
	}
//______________singleton thread-safe______________________
