import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private final JLabel resultLabel = new JLabel("Данные кредита ещё не введены.");
    private final JButton inputButton = new JButton("Ввести данные");

    public MainView() {
        setTitle("Кредит на жильё");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(resultLabel, BorderLayout.CENTER);
        add(inputButton, BorderLayout.SOUTH);
    }

    public JButton getInputButton() {
        return inputButton;
    }

    public void updateResults(double monthlyPayment, double overpaymentFactor) {
        resultLabel.setText(
                "<html>Ежемесячный платёж: " +
                        String.format("%.2f", monthlyPayment) +
                        "<br>Во сколько раз выплата больше кредита: " +
                        String.format("%.2f", overpaymentFactor) +
                        "</html>"
        );
    }
}
