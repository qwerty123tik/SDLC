import javax.swing.*;

public class CreditController {

    private final CreditModel model;
    private final MainView view;

    private double lastAmount = 0;
    private double lastRate = 0;
    private int lastYears = 0;

    public CreditController(CreditModel model, MainView view) {
        this.model = model;
        this.view = view;

        view.getInputButton().addActionListener(e -> processInput());

        model.addListener(evt ->
                view.updateResults(
                        model.getMonthlyPayment(),
                        model.getOverpaymentFactor()
                )
        );
    }

    private void processInput() {
        InputDialog dialog = new InputDialog(
                view,
                lastAmount,
                lastRate,
                lastYears
        );

        dialog.setVisible(true);

        if (!dialog.isConfirmed()) {
            return;
        }

        try {
            double amount = dialog.getAmount();
            double rate = dialog.getRate();
            int years = dialog.getYears();

            if (amount <= 0 || rate < 0 || years <= 0) {
                throw new IllegalArgumentException();
            }

            lastAmount = amount;
            lastRate = rate;
            lastYears = years;

            model.setData(amount, rate, years);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    view,
                    "Введите корректные числовые значения.",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    view,
                    "Сумма и срок должны быть больше нуля, " +
                            "а процентная ставка не может быть отрицательной.",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}