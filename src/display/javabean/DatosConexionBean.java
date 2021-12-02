package display.javabean;

import java.io.InputStream;
import java.util.Properties;

public class DatosConexionBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private static DatosConexionBean instance = null;
	
	private String DRIVER;
	private String URL;
	private String USUARIO;
	private String PASSWORD;
	private Properties prop;
	private InputStream fichero;
	
	private DatosConexionBean() {
		DRIVER = "";
		URL = "";
		USUARIO = "";
		PASSWORD = "";
		prop = new Properties();
		fichero = null;
	}
	
	public static DatosConexionBean getInstance() {
		if(instance == null) {
			instance = new DatosConexionBean();
		}
		
		return instance;
	}
	
	public String getDRIVER() {
		return DRIVER;
	}
	
	public void setDRIVER(String dRIVER) {
		DRIVER = dRIVER;
	}
	
	public String getURL() {
		return URL;
	}
	
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public String getUSUARIO() {
		return USUARIO;
	}
	
	public void setUSUARIO(String uSUARIO) {
		USUARIO = uSUARIO;
	}
	
	public String getPASSWORD() {
		return PASSWORD;
	}
	
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
	public void setFichero(InputStream filename) {
		fichero = filename;
	}
	
	public Properties getSQL() {
		try {
			InputStream s = fichero;
			prop.load(s);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return prop;
	}
}