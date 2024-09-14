# Java Application Gradle

## Install java-17

```
sudo yum install -y java-17 java-17-devel unzip
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-17.0.12.0.7-2.el9.x86_64/
export PATH=$PATH:/usr/lib/jvm/java-17-openjdk-17.0.12.0.7-2.el9.x86_64/bin/
```

## Download gradle and install it
```

wget https://services.gradle.org/distributions/gradle-8.10.1-bin.zip
unzip gradle-8.10.1-bin.zip

```

## build the application 
```
./gradlew clean build
```


## find the war file
```
find . -name "*.war"
```
