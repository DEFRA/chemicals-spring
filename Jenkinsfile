@Library('jenkins-shared-library')_

node (label: 'build_nodes') {
    def environmentVariables = [
        "RUN_SONAR=true",
        "SERVICE_NAME=REACH Spring",
        "PROJECT_REPO_URL=https://giteux.azure.defra.cloud/chemicals/reach-spring.git"
    ]

    withEnv(environmentVariables) {
        reachLibraryPipeline()
    }
}
