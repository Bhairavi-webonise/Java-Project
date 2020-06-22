package utilities
 
import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.jobs.FreeStyleJob
 
class GithubPipeline {
 
    String name
    String description
    String displayName
    String branchesName
    String urlRepo
    String credentialsId
 
 
    void build(DslFactory dslFactory) {
        def job = dslFactory.pipelineJob(name) {
            description(description)
            displayName(displayName)
                            
    //         scm {
    //                    git {
   //                         branch("\${BRANCH}") 
 //                           remote {
   //                             url(urlRepo)
   //                             credentials(credentialsId)
      //                      }
   //                     }
                        
    //                  
    //                }
                
            
  
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
        

           

            
        }
    }

}
