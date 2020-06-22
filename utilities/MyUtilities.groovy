package utilities

class MyUtilities {
 

    static void addMyFeature(def job) {
      job.with {
            description('production Environment : URL')
                
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

