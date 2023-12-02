import javax.swing.*;
import java.awt.*;

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

        convertButton = new JButton("Convert");
        convertButton.addActionListener(e -> convertButtonActionPerformed());

        Font largerFont = new Font("Arial", Font.PLAIN, 18);
        fromUnitComboBox.setFont(largerFont);
        toUnitComboBox.setFont(largerFont);
        quantityField.setFont(largerFont);

        // Set the preferred size of the quantityField
        quantityField.setPreferredSize(new Dimension(50, quantityField.getPreferredSize().height));

        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        mainPanel.setBackground(new Color(255, 255, 255));

        // Unit Label at the Top
        JLabel unitLabel = new JLabel("UNIT CONVERTER");
        Font labelFont = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30);
        unitLabel.setFont(labelFont);
        unitLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(unitLabel, BorderLayout.NORTH);

        JPanel roundedPanel = createRoundedPanel();
        mainPanel.add(roundedPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
    }

    private JPanel createRoundedPanel() {
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

        return roundedPanel;
    }

    private void setFrameVisible() {
        frame.setVisible(true);
    }

    private void convertButtonActionPerformed() {
        try {
            String fromUnit = (String) fromUnitComboBox.getSelectedItem();
            String toUnit = (String) toUnitComboBox.getSelectedItem();
            double quantity = Double.parseDouble(quantityField.getText());

            double result = converter.performConversion(fromUnit, toUnit, quantity);
            resultLabel.setText("Result: " + result + " " + toUnit );
           
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid quantity input", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error during conversion", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConverterGUI::new);
    }
}
