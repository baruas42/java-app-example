pipeline {
  agent { label 'worker-node'}


  environment {
    GIT_REPO = "https://github.com/someperson42/java-gradle"
    imagename = "saurab42/gradle-java"
    registryCredential = 'bca956f1-3624-424f-9a1e-5142d3a30e74'
    dockerPath = "."
    dockerContext = "."
    dockerImage = ''
    JAVA_HOME= "/usr/lib/jvm/java-17-openjdk-17.0.12.0.7-2.el9.x86_64"

  }
  
  triggers {
    pollSCM('* * * * *')
  }

  
  stages {
      stage('Clean') {
          
          steps {
              script {
                  cleanWs()   // clean workspace before starting build
              }
          }
      }
      stage("Clone Git Repository") {
          steps {
                git( 
                    url: "$GIT_REPO",
                    branch: "main",
                    changelog: true,
                    poll: true
                )
                echo "The git repo \"$GIT_REPO\" has beed cloned  "
          }
      }
      
      
      stage("Gradle clean build") {
          steps {
              sh '''
              ./gradlew clean build
              '''
          }
      }

      stage("Docker image build") {
          steps {
            script {
              dockerImage = docker.build(imagename, "$dockerPath")
            }
          }
      }
      
      stage("Docker image push") {
          steps {
              script {
                  docker.withRegistry('', registryCredential) {
                      dockerImage.push(env.BUILD_NUMBER)
                      dockerImage.push("latest")
                  }
              }
          }
      }
      

      stage("Archive Artifacts") {
          steps {
              archiveArtifacts artifacts: 'build/libs/*.war', followSymlinks: false
          }
      }
      
      stage("Deploy war/ear to a container") {
          steps {
              
              sh'ls -al build/libs/my-web-app.war'
              deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://192.168.56.107:8090/')], contextPath: null, war: 'build/libs/*.war'
          }
      }
  }
}

