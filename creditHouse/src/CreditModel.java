import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreditModel {
        private double amount;
        private double rate;
        private int years;

        private double monthlyPayment;
        private double totalPayment;
        private double overpaymentFactor;

        private final PropertyChangeSupport support = new PropertyChangeSupport(this);

        public void setData(double amount, double rate, int years) {
            this.amount = amount;
            this.rate = rate;
            this.years = years;
            calculate();
        }

        private void calculate() {
            int months = years * 12;
            double monthlyRate = rate / 100.0 / 12.0;  // месячная ставка

            if (monthlyRate == 0) {
                monthlyPayment = amount / months;   // случай нулевой ставки
            } else {
                double coefficient = Math.pow(1 + monthlyRate, months);
                monthlyPayment = amount * monthlyRate * coefficient / (coefficient - 1);
            }

            totalPayment = monthlyPayment * months;    // общая сумма выплат
            overpaymentFactor = totalPayment / amount;     // во сколько раз выплата больше кредита(коэф переплаты)

            support.firePropertyChange("credit", null, this);
        }

        public double getMonthlyPayment() {
            return monthlyPayment;
        }

        public double getOverpaymentFactor() {
            return overpaymentFactor;
        }

        public void addListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }
    }
