#!/usr/bin/env bash
if  [[ "$TRAVIS_SECURE_ENV_VARS" = "true" ]];then
    # The merge will not be deployed but the build after the bump pom will be deployed to maven central
    if [[ "$TRAVIS_PULL_REQUEST" = "false" ]] && [[ "$TRAVIS_BRANCH" = "master" ]] && ! [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Merge pull request.*bugfix) ]] && ! [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Merge pull request.*feature) ]] && ! [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Merge pull request.*clean) ]] ;then
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy sonar:sonar --settings .settings.xml -P release,ossrh
    else
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar
    fi
else
    mvn install
fi
