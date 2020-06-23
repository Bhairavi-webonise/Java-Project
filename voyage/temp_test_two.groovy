import JavaProject.utilities.*;

ProductionJob productionjob = new ProductionJob(
job_name: "example-job-2",
git_repo: "git@github.com:webonise/devops_DSL.git",
archive: "aurorpa-prod.tar.gz",
host_name: "aurorpa-prod-web-db",
deployment_script: "deploy-prod-aurorpa.sh",
describe: "aurorpa prodution env URL: aurorpa.com",
git_credentials: "ee329c9b-b5f7-47b6-b25e-4526cb14c55f"
)

productionjob.build(this)


