# SDKSampleCode
1. Put the sdk jar file under the project directory
2. Add dependency into the pom.xml
<dependency>
  <groupId>com.Insightfinder</groupId>
  <artifactId>InsightfinderSDK</artifactId>
  <version>1.1</version>
</dependency>

3. Then build
mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=InsightfinderSDK-1.1.jar
