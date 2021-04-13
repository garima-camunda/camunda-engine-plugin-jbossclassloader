# Steps on how to install this plugin in JBoss


* Copy the **camunda-engine-plugin-jbossclassloader-1.0.jar** artifact to **$JBOSS_HOME/modules/org/camunda/bpm/camunda-engine-plugin-jbossclassloader/main**
* Create a **module.xml** file in the same directory with the following contents:
```java
<module xmlns="urn:jboss:module:1.0" name="org.camunda.bpm.camunda-engine-plugin-jbossclassloader">
  <resources>
    <resource-root path="camunda-engine-plugin-jbossclassloader-1.0.jar" />
  </resources>

  <dependencies>
  	<module name="org.camunda.bpm.camunda-engine" />
    <module name="javax.api"/>
    <module name="org.slf4j" />
  </dependencies>
</module>
```
* Add the module dependency to the file **$JBOSS_HOME/modules/org/camunda/bpm/camunda-engine-plugins/main/module.xml** and set the attribute **export="true"** to make sure that the module is visible in the classpath of Camunda's subsystem
```
    <module xmlns="urn:jboss:module:1.0"
            name="org.camunda.bpm.camunda-engine-plugins">
      <dependencies>
        <!-- ... -->
        <module name="org.camunda.bpm.camunda-engine-plugin-jbossclassloader" export="true" />
      </dependencies>
    </module>
```
* Add the following under the <plugins> tag of your Camunda Process Engine Configuration in standalone.xml:
```
<plugin>
    <class>org.camunda.jboss.plugin.classloader.JBossClassloaderPlugin</class>
</plugin>
```
* Start/Restart JBoss

**Note:** This was tested on 7.10.2-ee and JBoss EAP 7.2
