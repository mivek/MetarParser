#!/usr/bin/env bash
if  [[ "$TRAVIS_SECURE_ENV_VARS" = "true" ]];then
    # The merge will not be deployed but the build after the bump pom will be deployed to maven central
    if [[ "$TRAVIS_PULL_REQUEST" = "false" ]] && [[ "$TRAVIS_BRANCH" = "master" ]] && [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Bump pom) ]] ;then
        export MAVEN_OPTS="--add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.lang.reflect=ALL-UNNAMED --add-opens=java.base/java.text=ALL-UNNAMED --add-opens=java.desktop/java.awt.font=ALL-UNNAMED"
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy sonar:sonar --settings .settings.xml -P release,ossrh
    else
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar
    fi
else
    mvn clean install
fi
