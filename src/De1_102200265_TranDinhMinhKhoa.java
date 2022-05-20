import java.sql.Connection;
import java.sql.DriverManager;

public class De1_102200265_TranDinhMinhKhoa {
    public static void main(String[] args){
        String dbURL="jdbc:postgresql://localhost/?user=postgres&password=140521";
        try{
            Connection conn= DriverManager.getConnection(dbURL);
            if (conn!=null ) {
                System.out.println("Connected");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        MainFrame m=new MainFrame();
    }
}
