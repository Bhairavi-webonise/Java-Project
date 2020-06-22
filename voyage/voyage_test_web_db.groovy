job("voyage-test-web-db") {
    description "voyage test env  url : voyage.weboapp.com"
    
    parameters {
        stringParam('branch', 'master', 'Branch to deploy')
    }
  
   
    scm {
        git {
            remote {
                
                url('git@github.com:webonise/voyage.git')
                credentials('ee329c9b-b5f7-47b6-b25e-4526cb14c55f')
            }
             branch("\${BRANCH}") 
            }
        }

    steps {

        shell "#!/bin/bash \n set -xe \n rm -rf .git* \n tar -zvcf ../voyage-test.tar.gz . \n scp ../voyage-test.tar.gz voyage-test-web-db:/home/deploy/build/ \n rm -rf ../voyage-test.tar.gz \n ssh voyage-test-web-db /opt/scripts/deploy-voyage.weboapps.com.sh"       
    }
  
    wrappers {
        preBuildCleanup { // Clean before build
            
            deleteDirectories()
            cleanupParameter('')
        }
    }
    
 }
