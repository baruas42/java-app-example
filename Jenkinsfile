pipeline {
  agent { label 'worker-node'}


  environment {
    GIT_REPO = "https://github.com/baruas42/java-gradle"
    imagename = "saurab42/jenkins-app"
    registryCredential = 'docker-cred'
    dockerPath = "."
    dockerContext = "."
    dockerImage = ''
    JAVA_HOME= "/home/saurab/jdk17/"

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
              sh'ls -al build/libs/*.war'
              deploy adapters: [tomcat9(credentialsId: 'tomcat-cred', path: '', url: 'http://10.0.0.17:7080/')], contextPath: null, war: 'build/libs/*.war'
          }
      }
  }
}
