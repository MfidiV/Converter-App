import javax.swing.JOptionPane;
import java.util.HashSet;
import java.util.Set;

public class Converter {
    private Set<String> supportedConversions;

    public Converter() {
        initializeSupportedConversions();
    }

    private void initializeSupportedConversions() {
        supportedConversions = new HashSet<>();
        supportedConversions.add("feet-meters");
        supportedConversions.add("meters-feet");
        supportedConversions.add("pounds-kgs");
        supportedConversions.add("kgs-pounds");
        supportedConversions.add("fahrenheit-celsius");
        supportedConversions.add("celsius-fahrenheit");
    }

    public double performConversion(String fromUnit, String toUnit, double quantity) {
        try {
            if (!isConversionSupported(fromUnit, toUnit)) {
                JOptionPane.showMessageDialog(null, "Conversion from " + fromUnit + " to " + toUnit + " not supported", "Error", JOptionPane.ERROR_MESSAGE);
                return 0.0;
            }

            switch (fromUnit.toLowerCase()) {
                case "feet":
                    return convertFeetToMeters(quantity, toUnit);
                case "meters":
                    return convertMetersToFeet(quantity, toUnit);
                case "pounds":
                    return convertPoundsToKgs(quantity, toUnit);
                case "kgs":
                    return convertKgsToPounds(quantity, toUnit);
                case "fahrenheit":
                    return convertFahrenheitToCelsius(quantity, toUnit);
                case "celsius":
                    return convertCelsiusToFahrenheit(quantity, toUnit);
                default:
                    JOptionPane.showMessageDialog(null, "Don't even try", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0.0;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity input", "Error", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during conversion", "Error", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
    }

  
private boolean isConversionSupported(String fromUnit, String toUnit) {
     /**
 * Checks if conversion is supported between the specified units.
 */
    // Create a conversion pair using lowercase units
    String conversionPair = fromUnit.toLowerCase() + "-" + toUnit.toLowerCase();

    // Check if the conversion pair is present in the list of supported conversions
    return supportedConversions.contains(conversionPair);
}

    private double convertFeetToMeters(double feet, String toUnit) {
        return toUnit.equalsIgnoreCase("meters") ? feet * 0.3048 : feet;
    }

    private double convertMetersToFeet(double meters, String toUnit) {
        return toUnit.equalsIgnoreCase("feet") ? meters / 0.3048 : meters;
    }

    private double convertPoundsToKgs(double pounds, String toUnit) {
        return toUnit.equalsIgnoreCase("kgs") ? pounds * 0.453592 : pounds;
    }

    private double convertKgsToPounds(double kgs, String toUnit) {
        return toUnit.equalsIgnoreCase("pounds") ? kgs / 0.453592 : kgs;
    }

    private double convertFahrenheitToCelsius(double fahrenheit, String toUnit) {
        
        return toUnit.equalsIgnoreCase("celsius") ? (fahrenheit - 32) * 5/9 : fahrenheit;
    }

    private double convertCelsiusToFahrenheit(double celsius, String toUnit) {
        return toUnit.equalsIgnoreCase("fahrenheit") ? (celsius * 9/5) + 32 : celsius;
    }
}
