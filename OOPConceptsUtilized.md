# Encapsulation:

The `Converter` class effectively encapsulates the conversion logic, employing principles of information hiding and access control:

- **Private Conversion Methods:** The conversion methods within the `Converter` class are declared as private. This encapsulates the details of individual conversions, ensuring that they are not directly accessible from outside the class.

- **Private Set (`supportedConversions`):** The `supportedConversions` set is declared as private, restricting direct access. Clients interact with it through the public method `isConversionSupported()`. This encapsulation shields the internal implementation of supported conversions.

# Abstraction:

The `Converter` class employs abstraction to provide a simplified interface for clients:

- **High-Level Method (`performConversion()):** The `performConversion()` method serves as a high-level abstraction, hiding the complexity of individual conversion steps. Clients can use this method without needing to understand the intricacies of each conversion.
```java
// Example code snippet
public double performConversion(String fromUnit, String toUnit, double quantity) {
    // Conversion logic...
}

```

# Inheritance:

Inheritance is not explicitly used in this code; however, composition and delegation principles are applied:

```java
// Example code snippet
public class ConverterGUI {
    private Converter converter;
    // Other class members...
}

```

- **Composition in `ConverterGUI`:** The `ConverterGUI` class contains an instance of the `Converter` class (`converter`). This relationship demonstrates composition, where one class is composed of another to achieve a higher-level functionality.



# Composition:

The `ConverterGUI` class showcases the composition over inheritance principle:

- **Composition with `Converter`:** The `ConverterGUI` class composes the `Converter` class to utilize its conversion capabilities. This design choice favors composition, promoting flexibility and maintainability over relying on inheritance.

```java
// Example code snippet
public class ConverterGUI {
    private Converter converter;
    // Other class members...
}

```

# javax.swing Package:

Java's `javax.swing` package is commonly used for building GUIs, providing lightweight components:

- **Components:** Components like `JFrame`, `JPanel`, `JButton`, `JLabel`, etc., belong to this package.

- **Lightweight GUI:** These components are considered lightweight compared to their AWT counterparts, providing more flexibility.

- **Example:**

  ```java
  import javax.swing.JButton;
  import javax.swing.JFrame;

# Combining Both:

In Java GUI development, it's common to import classes from both `javax.swing` and `java.awt` packages, especially in complex interfaces.

## Usage Scenario:

- **Main Structure (`javax.swing`):** Components like `JFrame`, `JPanel` from `javax.swing` are often used for constructing the main structure of the GUI. These components offer a more modern and versatile set of GUI elements.

- **Low-Level Operations (`java.awt`):** Classes from `java.awt` might be employed for specific low-level operations or custom drawing requirements in the GUI.

## Example:

```java
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
```

