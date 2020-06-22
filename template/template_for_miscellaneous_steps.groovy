job("template-miscellaneous-db") {
    description " prod env wordpress bitnami site  URL : project.com"
    
   
    scm {
        git {
            remote {
                
                url('git@github.com:webonise/project.git')
                credentials('ee32shs9c9b-b5f7-47bshs6-b25e4shjhjsc55f')
            }
            branch("master")  
            }
        }


    steps {

        shell "#/bin/bash \n set -x \n tar -zvcf project-prod.tar.gz ./*"
       
    }
  
    wrappers {
        preBuildCleanup { // Clean before build
            
            deleteDirectories()
            cleanupParameter('')
        }
    }
    
    
    publishers {
        publishOverSsh {
            server('project-prod-web-db') {
                label('project-prod-web-db')
                transferSet {
                    sourceFiles('**/project-prod.tar.gz')
                    execCommand('/opt/scripts/deployment_script.sh')
                            }
                                    }

                        }
                }
   
 }

