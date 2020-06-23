package utilities

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
 
public class ProductionJob{
 
String job_name
String git_repo
String archive
String host_name
String deployment_script
String describe
String git_credentials
Job job
 
void build(DslFactory dslFactory) {
	this.job = dslFactory.job(job_name) {
	
       description describe
       parameters {
         	 activeChoiceParam('start_deployment')
					 {
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
                url(git_repo)
                credentials(git_credentials)
            }
             branch("master") 
            }
    }


    steps {
        shell '#!/bin/bash \n set -xe \n rm -rf .git* \n if [ "${start_deployment}" == "Yes"]  \n then \n echo "deployment has started" \n tar -zvcf ../'+ archive +' . \n scp ../'+ archive +' '+ host_name +':/home/deploy/build/ \n rm -rf ../'+ archive +' \n ssh '+ host_name +' sudo sh /opt/scripts/'+ deployment_script +' \n  else \n echo "deployment aborted Please select Yes to Start deployment" \n fi'       
          }
  wrappers {
        	preBuildCleanup 
                       { // Clean before build
                       		 deleteDirectories()
            	         	 cleanupParameter('')
                       }
                 }      	
    }
  }
}
