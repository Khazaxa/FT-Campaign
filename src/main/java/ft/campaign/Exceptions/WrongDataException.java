package ft.campaign.Exceptions;

public class WrongDataException extends Exception {
    public WrongDataException(String name) {
        super("A campaign with provided name " + name + " already exists.");
    }
}
