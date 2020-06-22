def gitrepo = 'project/webonise.git'

job("template-prod-web-db") {
    description "template to define the make it modularised."
    
   
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
                
                url(gitrepo)
                credentials('ee329c9b-b5f7-47b6-b25e-4526cb14c55f')
            }
             branch("master") 
            }
        }

   
   
    
 }

