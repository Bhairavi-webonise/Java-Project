import utilities.MyUtilities

def gitrepo = 'project/webonise.git'
def gitcredentials = 'ee329c9b-b5f7-47b6-b25e-4526cb14c55f'
def tarfile = 'aurorpa-prod.tar.gz'
def hostname = 'aurorpa-prod-web-db'
def deploymentscript = 'deploy-prod-aurorpa.sh'
def myJob = job('prod-env')

{
    scm {
        git {
            remote {
                
                url(gitrepo)
                credentials(gitcredentials)
            }
             branch("master") 
            }
    }

    steps {

        shell '#!/bin/bash \n set -xe \n rm -rf .git* \n if [ "${start_deployment}" == "Yes"]  \n then \n echo "deployment has started" \n tar -zvcf ../'+ tarfile +' . \n scp ../'+ tarfile +' '+ hostname +':/home/deploy/build/ \n rm -rf ../'+ tarfile +' \n ssh '+ hostname +' sudo sh /opt/scripts/'+ deploymentscript +' \n  else \n echo "deployment aborted Please select Yes to Start deployment" \n fi'       
          }


}
MyUtilities.addMyFeature(myJob)
