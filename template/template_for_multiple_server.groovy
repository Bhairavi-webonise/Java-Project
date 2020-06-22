job("project-test-multiple-web-db") {
    description "project test environment \n URL : project.testing.com"
    

      // ####################  Block start ####################################
      // this block of code can be used on in test and stage environment, for production environment it can be skipped. 
      parameters {                        
        stringParam('branch', 'master', 'Branch to deploy')
                 } 
      //#####################  Block ends ###############################################
  
      //###################### Block start for parameter to confirm yes/no for deployment #################
      parameters {
          activeChoiceParam('start_deployment') {
            description('Do you want to proceed for deployment')
            choiceType('RADIO')
            groovyScript {
                script('["Yes","No"]')
            }
        }
       }

      //########################## block ends ############################################

      // ####################### Block start #############################################
      // Below block of code is used where we need to deploy for multiple server. 
      parameters {
          activeChoiceParam('deploy_server') {
            description('Select server to deploy')
            choiceType('CHECKBOX')
            groovyScript {
                script('["project-prod-web-1","project-prod-web-2"]')   // add the host name similar present in webonise-ssh-config.
                }
           }
      }
      // ######################### Block end ##############################################
     
      // ################ Git Details Block start ##########################################
      scm {
        git {
            remote {
                url('git@github.com:Project123/project.git')      // change the repo url. 
                credentials('ee329c9b-b8977-47b6-b25e-4524c905f') // please do verify this credtials from the jenkins
            }
            branch("\${BRANCH}") // OR if parameter block is not used use << branch ("master") >> if we are using the production environment where we keep defualt as master branch to deploy. 
              
            }
        }

      //############ Below block include step to be taken before deployment ##############
      // this block is used where we need to deploy on multiple server or else use diffrent block ####
      steps {
        shell '#!/bin/bash \n  set -xe \n if [ "${start_deployment}" == "Yes" ]  \n then \n echo "deployment has started" \n   \n rm -rf .git* \n tar -zvcf ../project-test.tar.gz . \n for server in $(echo $deploy_server | sed "s/,/ /g") \n do \n scp ../project-test.tar.gz $server:/home/deploy/build/ \n ssh $server /opt/scripts/deployment_script.sh \n done \n rm -rf ../project-test.tar.gz  \n  else \n echo "deployment aborted Please select Yes to Start deployment" \n exit 1 \n fi'

       }
  
      //############ block ends ##########################################################

     // ########## post deployment tasks starts ########################################## 
     wrappers {
        preBuildCleanup { // Clean before build
            deleteDirectories()
            cleanupParameter('')
        }
      }
     // ##########block end ##############################################################

    
 }



