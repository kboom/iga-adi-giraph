package edu.agh.iga.adi.giraph.direction.test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EnvironmentVariables {
  private final Map<String, String> buffer = new HashMap<>();
  private boolean statementIsExecuting = false;

  public EnvironmentVariables() {
  }

  public static EnvironmentVariables withEnvironmentVariables() {
    return new EnvironmentVariables();
  }

  public EnvironmentVariables set(String name, String value) {
    if (this.statementIsExecuting) {
      this.writeVariableToEnvMap(name, value);
    } else {
      this.writeVariableToBuffer(name, value);
    }

    return this;
  }

  public EnvironmentVariables clear(String... names) {
    String[] arr$ = names;
    int len$ = names.length;

    for (int i$ = 0; i$ < len$; ++i$) {
      String name = arr$[i$];
      this.set(name, null);
    }

    return this;
  }

  private void writeVariableToEnvMap(String name, String value) {
    this.set(getEditableMapOfVariables(), name, value);
    this.set(getTheCaseInsensitiveEnvironment(), name, value);
  }

  private void set(Map<String, String> variables, String name, String value) {
    if (variables != null) {
      if (value == null) {
        variables.remove(name);
      } else {
        variables.put(name, value);
      }
    }

  }

  private void writeVariableToBuffer(String name, String value) {
    this.buffer.put(name, value);
  }

  private void copyVariablesFromBufferToEnvMap() {

    for (Map.Entry<String, String> stringStringEntry : this.buffer.entrySet()) {
      this.writeVariableToEnvMap(
          stringStringEntry.getKey(),
          stringStringEntry.getValue()
      );
    }

  }

  public <T> T runWithVariables(Supplier<T> producer) {
    return new EnvironmentVariables.EnvironmentVariablesStatement<>(producer).evaluate();
  }

  private static Map<String, String> getEditableMapOfVariables() {
    Class classOfMap = System.getenv().getClass();

    try {
      return getFieldValue(classOfMap, System.getenv(), "m");
    } catch (IllegalAccessException var2) {
      throw new RuntimeException("System Rules cannot access the field 'm' of the map System.getenv().", var2);
    } catch (NoSuchFieldException var3) {
      throw new RuntimeException("System Rules expects System.getenv() to have a field 'm' but it has not.", var3);
    }
  }

  private static Map<String, String> getTheCaseInsensitiveEnvironment() {
    try {
      Class<?> processEnvironment = Class.forName("java.lang.ProcessEnvironment");
      return getFieldValue(processEnvironment, null, "theCaseInsensitiveEnvironment");
    } catch (ClassNotFoundException var1) {
      throw new RuntimeException("System Rules expects the existence of the class java.lang.ProcessEnvironment but it does not exist.", var1);
    } catch (IllegalAccessException var2) {
      throw new RuntimeException("System Rules cannot access the static field 'theCaseInsensitiveEnvironment' of the class java.lang.ProcessEnvironment.", var2);
    } catch (NoSuchFieldException var3) {
      return null;
    }
  }

  private static Map<String, String> getFieldValue(Class<?> klass, Object object, String name) throws NoSuchFieldException, IllegalAccessException {
    Field field = klass.getDeclaredField(name);
    field.setAccessible(true);
    return (Map) field.get(object);
  }

  private class EnvironmentVariablesStatement<T> {
    private final Supplier<T> supplier;
    private Map<String, String> originalVariables;

    EnvironmentVariablesStatement(Supplier<T> supplier) {
      this.supplier = supplier;
    }

    public T evaluate() {
      this.saveCurrentState();
      EnvironmentVariables.this.statementIsExecuting = true;

      try {
        EnvironmentVariables.this.copyVariablesFromBufferToEnvMap();
        return supplier.get();
      } finally {
        EnvironmentVariables.this.statementIsExecuting = false;
        this.restoreOriginalVariables();
      }
    }

    void saveCurrentState() {
      this.originalVariables = new HashMap<>(System.getenv());
    }

    void restoreOriginalVariables() {
      this.restoreVariables(EnvironmentVariables.getEditableMapOfVariables());
      Map<String, String> theCaseInsensitiveEnvironment = EnvironmentVariables.getTheCaseInsensitiveEnvironment();
      if (theCaseInsensitiveEnvironment != null) {
        this.restoreVariables(theCaseInsensitiveEnvironment);
      }

    }

    void restoreVariables(Map<String, String> variables) {
      variables.clear();
      variables.putAll(this.originalVariables);
    }
  }
}
