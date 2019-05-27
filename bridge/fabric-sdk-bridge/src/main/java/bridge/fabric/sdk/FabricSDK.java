package bridge.fabric.sdk;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *
 */
public interface FabricSDK {

    FabricVersion getVersion();

    /**
     * call `s method including different content
     */
    String callDifferentMethods();

    /**
     * Display target class's method and parameters
     *
     * e.g) calculateBlockHash(long, byte[], byte[])
     */
    default void displayDifferentMethods(Class<?> clazz, String methodName) {
        System.out.println("===============================");
        System.out.printf("Display %s::%s \n", clazz.getName(), methodName);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }

            StringBuilder builder = new StringBuilder("Found method : ")
                .append(methodName)
                .append("(");

            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                builder.append(parameter.getType().getSimpleName())
                    .append(" ")
                    .append(parameter.getName());

                if (i == parameters.length - 1) {
                    builder.append(")");
                } else {
                    builder.append(", ");
                }
            }

            System.out.println(builder);
        }
    }
}
