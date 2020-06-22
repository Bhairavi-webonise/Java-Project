job("template-single-web-app-db")  // keep the name same as hostname of the server
{
    description "project test URL : project.test.com"
    
    
    parameters {
        stringParam('branch', 'master', 'Branch to deploy')
    }
  
     parameters {
          activeChoiceParam('start_deployment') {
            description('Do you want to proceed for deployment')
            choiceType('RADIO')
            groovyScript {
                script('["Yes","No"]')
            }
        }
    }

    scm {
        git {
            remote {
                
                url('git@github.com:project/project-test.git') // make the changes.
                credentials('ee329c9b-b5f7-47b6-b25s26cb14c55f')
            }
             branch("\${BRANCH}")  // it will take the branch from the prameter given while deployment
            }
        }

    steps {
        shell '#!/bin/bash \n set -xe \n if [ "${start_deployment}" == "Yes" ]  \n then \n echo "deployment has started" \n  rm -rf .git* \n tar -zvcf ../project-test.tar.gz . \n scp ../project-test.tar.gz project-test-web-app:/home/deploy/build/ \n rm -rf ../project-test.tar.gz \n ssh project-test-web-app /opt/scripts/deployment_script.sh  \n  else \n echo "deployment aborted Please select Yes to Start deployment" \n exit 1 \n fi'       
    }
  
    wrappers {
        preBuildCleanup { // Clean before build
            
            deleteDirectories()
            cleanupParameter('')
        }
    }
   
 }

