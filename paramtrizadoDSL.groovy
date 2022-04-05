job('ejemplo2-job-DSL'){
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
            node / gitConfigName('BuhM0')
            node / gitConfigEmail('corvus164@gmail.com')
        }
  }
  parameters {
    booleanParam('agente', false)
    choiceParam('planeta', ['Mercurio', 'Jupiter', 'Venus', 'Tierra', 'Marte', 'Saturno', 'Urano', 'Neptuno'])
    stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el job booleano')
  }
  
  triggers {
    cron('H/7 * * * *')
  }
  
  steps {
    shell("jobscript.sh")
  }
  
  publishers {
    mailer('pedroabmin01@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
