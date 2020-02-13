
import java.io.File;

/**
 * @author Abner Hernandez
 */

public class Init {
    public static String folder;
    public static Panel_Init panel;
    
    public static void main(String[] args) {
        folder = create_folder_reports();
        panel = new Panel_Init();
        panel.setVisible(true);
    }
    
    private static String create_folder_reports()
    {
        File file = new File("Reports");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
                return file.getAbsolutePath();
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        return file.getAbsolutePath();
    }
}
