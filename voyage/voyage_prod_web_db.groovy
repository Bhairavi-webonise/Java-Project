job("voyage-prod-web-db") {
   description "voyage prod env  url : voyage.webonise.com"
   

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
                   url('git@github.com:webonise/voyage.git')
                   credentials('ee329c9b-b5f7-47b6-b25e-4526cb14c55f')
                   }
              branch("master")
            }
        }

    steps {
        shell '#!/bin/bash \n set -xe \n if [ "${start_deployment}" == "Yes" ]  \n then \n echo "deployment has started" \n  rm -rf .git* \n tar -zvcf ../voyage-prod.tar.gz . \n scp ../voyage-prod.tar.gz voyage-prod-web-db:/home/deploy/build/ \n rm -rf ../voyage-prod.tar.gz \n ssh voyage-prod-web-db /opt/scripts/deploy-production-voyage.sh \n else \n echo "deployment aborted Please select Yes to Start deployment" \n exit 1 \n fi'       
          }
  
    wrappers {
        preBuildCleanup { // Clean before build
            
            deleteDirectories()
            cleanupParameter('')
        }
    }
    
 }

