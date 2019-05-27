## Demo for supporting multiple jar with different version  

This demo project is support maven multiple version of same dependency  

For example, if u  want to use both fabric-sdk-1.3.0 and fabric-sdk-1.4.0 depends on requests,  

then we can create multi projects like below  

> Maven multi projects

```aidl
├── bridge                          <-- bridge project
│   ├── fabric-sdk-10         <-- fabric sdk impl project with version 1.0.0
│   ├── fabric-sdk-14         <-- fabric sdk impl project with version 1.4.0
│   ├── fabric-sdk-bridge  <-- fabric sdk interface for supporting multiple version 
│   └── pom.xml               <-- bridge pom.xml
├── pom.xml                     <-- parent pom.xml
└── server                          <-- server project pom.xml
    ├── pom.xml                 <-- server pom.xml
```      

> Getting started  

```
$ git clone https://github.com/zacscoding/support-multiple-version.git
$ cd support-multiple-version
$ chmod +x ./mvnw
$ mvnw install
$ java -jar server/target/server-1.0-SNAPSHOT.jar
```    

```aidl
$ curl -XGET http://localhost:8080/call/V1_0
// server log
===============================
Display one.zero.org.hyperledger.fabric.sdk.SDKUtils::calculateBlockHash 
Found method : calculateBlockHash(long arg0, byte[] arg1, byte[] arg2)

$ curl -XGET http://localhost:8080/call/V1_4
// server log
===============================
Display one.four.org.hyperledger.fabric.sdk.SDKUtils::calculateBlockHash 
Found method : calculateBlockHash(HFClient arg0, long arg1, byte[] arg2, byte[] arg3)
```

--- 

> Fabric SDK impl maven build

```aidl
    <build>
        <plugins>
            ...             
            <plugin>
                <artifactId>maven-shade-plugin</artifactId>
                <version>2.4.3</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <relocations>
                                <relocation>
                                    <pattern>org.hyperledger</pattern>
                                    <shadedPattern>one.zero.org.hyperledger</shadedPattern>
                                </relocation>
                            </relocations>
                            <createDependencyReducedPom>false</createDependencyReducedPom>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```  

=> we can relocate projects name by using maven-shade-plugin  
=> then sdk project is compiled like below  

> Origin SDK code  

```aidl
package org.hyperledger.fabric.sdk;
...
public class SDKUtils {
  ...
}

```  

> Relocated SDK code in jar  

```aidl
package one.zero.org.hyperledger.fabric.sdk;
...
public class SDKUtils {
  ...
}
```  

---  

For example, org.hyperledger.fabric.sdk.SDKUtils class has different args but same method name.  

> SDKUtils (1.0.0)

```aidl
SDKUtils::calculateBlockHash(long, byte[], byte[])
```

> SDKUtils (1.4.0)

```aidl
SDKUtils::calculateBlockHash(HFClient, long, byte[], byte[])
```

> FabricSDK interface  in fabric-sdk-bridge module

```aidl
public interface FabricSDK {
    ...
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
```  

> FabricSDK100 in fabric-sdk-10 module  

```aidl
public class FabricSDK100 implements FabricSDK { 
      ...
      @Override
      public String callDifferentMethods() {
          try {
              displayDifferentMethods(SDKUtils.class, "calculateBlockHash");
              // long blockNumber, byte[] previousHash, byte[] dataHash
              SDKUtils.calculateBlockHash(
                  1L,
                  new byte[0],
                  new byte[0]
              );
  
              return null;
          } catch (Exception e) {
              return e.getMessage();
          }
      }
}
  

```

> FabricSDK140  in fabric-sdk-14 module  

```aidl
public class FabricSDK140 implements FabricSDK { 
    ...  
    @Override
    public String callDifferentMethods() {
        try {
            displayDifferentMethods(SDKUtils.class, "calculateBlockHash");
            SDKUtils.calculateBlockHash(
                null,
                1L,
                new byte[0],
                new byte[0]
            );
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}  
```  

> Call result  

```aidl
===============================
// FabricSDK100::callDifferentMethods()
Display one.zero.org.hyperledger.fabric.sdk.SDKUtils::calculateBlockHash 
Found method : calculateBlockHash(long arg0, byte[] arg1, byte[] arg2)


// FabricSDK140::callDifferentMethods()
===============================
Display one.four.org.hyperledger.fabric.sdk.SDKUtils::calculateBlockHash 
Found method : calculateBlockHash(HFClient arg0, long arg1, byte[] arg2, byte[] arg3)
```









