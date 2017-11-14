/**
 * Created by Nariman on 2017-11-14.
 */
public class SessionManager {

    DBSession sess;

    public SessionManager(String sessionType, String username, String password) {
        if(sessionType.equals("Employee")){
            sess = new EmployeeSession(username,password);
            EmployeeSession empSess = (EmployeeSession) sess;
            empSess.executeQuery("sdfasd");
        }

    }
}
