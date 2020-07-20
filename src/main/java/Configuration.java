import java.nio.file.Paths;

public class Configuration {

    public static final String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
    public static final String imagePath = "\\src\\main\\resources";
}
