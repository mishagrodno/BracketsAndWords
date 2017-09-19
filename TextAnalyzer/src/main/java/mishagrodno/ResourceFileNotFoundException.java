package mishagrodno;

public class ResourceFileNotFoundException extends Exception {
    private String fileLocation;

    public ResourceFileNotFoundException(String fileLocation){
        super(fileLocation);
        this.fileLocation = fileLocation;
    }
}
