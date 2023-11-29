import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterGUI {
    private JFrame frame;
    private JComboBox<String> fromUnitComboBox;
    private JComboBox<String> toUnitComboBox;
    private JTextField quantityField;
    private JLabel resultLabel;
    private JButton convertButton;
    private Converter converter;  

    public ConverterGUI() {
        frame = new JFrame("Unit Converter");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        converter = new Converter();

        initComponents();
        addComponents();
        setFrameVisible();
    }

    private void initComponents() {
        String[] units = {"Feet", "Meters", "Pounds", "Kgs", "Fahrenheit", "Celsius"};

        fromUnitComboBox = new JComboBox<>(units);
        toUnitComboBox = new JComboBox<>(units);
        quantityField = new JTextField();
        resultLabel = new JLabel("Result:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        quantityField.setForeground(Color.GRAY);
        quantityField.setText("Enter quantity");
        quantityField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (quantityField.getText().equals("Enter quantity")) {
                    quantityField.setText("");
                    quantityField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (quantityField.getText().isEmpty()) {
                    quantityField.setForeground(Color.GRAY);
                    quantityField.setText("Enter quantity");
                }
            }
        });

        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertButtonActionPerformed();
            }
        });

        Font largerFont = new Font("Arial", Font.PLAIN, 14);
        fromUnitComboBox.setFont(largerFont);
        toUnitComboBox.setFont(largerFont);
        quantityField.setFont(largerFont);
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        mainPanel.setBackground(new Color(255, 255, 255));

        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int arc = 20;
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            }
        };
        roundedPanel.setLayout(new GridBagLayout());
        roundedPanel.setBackground(new Color(128, 128, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        roundedPanel.add(new JLabel("From Unit:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        roundedPanel.add(fromUnitComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        roundedPanel.add(new JLabel("To Unit:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        roundedPanel.add(toUnitComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        roundedPanel.add(new JLabel("Quantity:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        roundedPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        roundedPanel.add(convertButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        roundedPanel.add(resultLabel, gbc);

        mainPanel.add(roundedPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
    }

    private void setFrameVisible() {
        frame.setVisible(true);
    }

    private void convertButtonActionPerformed() {
        try {
            String fromUnit = (String) fromUnitComboBox.getSelectedItem();
            String toUnit = (String) toUnitComboBox.getSelectedItem();
            double quantity = Double.parseDouble(quantityField.getText());

            if (quantity < 0) {
                JOptionPane.showMessageDialog(frame, "Don't ", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if quantity is less than 0
            }

            double result = converter.performConversion(fromUnit, toUnit, quantity);
            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid quantity input", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error during conversion", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConverterGUI();
            }
        });
    }
}