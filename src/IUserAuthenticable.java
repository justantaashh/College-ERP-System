public interface IUserAuthenticable {
    boolean verifyPassword(String password);
    void displayMenu();
}
