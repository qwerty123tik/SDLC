import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            CreditModel model = new CreditModel();
            MainView view = new MainView();

            new CreditController(model, view);

            view.setVisible(true);
        });
    }
}