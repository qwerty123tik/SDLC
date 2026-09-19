import javax.swing.*;
import java.awt.*;

public class InputDialog extends JDialog {

    private final JTextField amountField = new JTextField();
    private final JTextField rateField = new JTextField();
    private final JTextField yearsField = new JTextField();

    private boolean confirmed = false;

    public InputDialog(JFrame parent, double lastAmount,
                       double lastRate, int lastYears) {

        super(parent, "Ввод данных кредита", true);

        setSize(400, 250);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Сумма кредита:"));
        amountField.setText(lastAmount > 0 ? String.valueOf(lastAmount) : "");
        panel.add(amountField);

        panel.add(new JLabel("Процентная ставка (%):"));
        rateField.setText(lastRate > 0 ? String.valueOf(lastRate) : "");
        panel.add(rateField);

        panel.add(new JLabel("Срок кредита (лет):"));
        yearsField.setText(lastYears > 0 ? String.valueOf(lastYears) : "");
        panel.add(yearsField);

        JButton okButton = new JButton("Рассчитать");
        JButton cancelButton = new JButton("Отмена");

        panel.add(okButton);
        panel.add(cancelButton);

        add(panel);

        okButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public double getAmount() {
        return Double.parseDouble(amountField.getText());
    }

    public double getRate() {
        return Double.parseDouble(rateField.getText());
    }

    public int getYears() {
        return Integer.parseInt(yearsField.getText());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Ошибка",
                JOptionPane.ERROR_MESSAGE
        );
    }
}