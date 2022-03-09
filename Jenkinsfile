pipeline {
  agent any
  tools {
   maven 'mvn 3.8.4'
   jdk 'java 8'
        }
  stages {
    stage("Build"){
        steps{
            git 'https://github.com/PavelLyutov/IT-Talants-Final-Project-kinoarena.git'
            dir('KinoArena') {
                   bat 'mvn clean compile'
            }
        }
    }
    stage("Unit Test"){
        steps{
                    dir('KinoArena') {
                           bat 'mvn clean verify'
                    }
        }
    }

     }
  }

