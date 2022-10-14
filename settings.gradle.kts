rootProject.name = "complaint-microservice"
include("app")

plugins {
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "1.0.22"
}

gitHooks {
    file(".git/hooks").mkdirs()
    commitMsg {
        conventionalCommits {
            defaultTypes()
            types("deps")
        }
    }
    preCommit {
        tasks("ktlintCheck")
    }
    createHooks()
}
