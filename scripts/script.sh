#!/usr/bin/env bash
if  [ "$TRAVIS_SECURE_ENV_VARS" = "true" ];then
    # The merge will not be deployed but the build after the bump pom will be deployed to maven central
    if [ "$TRAVIS_PULL_REQUEST" = "false" ] && [ "$TRAVIS_BRANCH" = "master" ] && ! [[ $1 =~ (Merge pull request.*bugfix) ]] && ! [[ $1 =~ (Merge pull request.*feature) ]] && ! [[ $1 =~ (Merge pull request.*clean) ]] ;then
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy sonar:sonar --settings .settings.xml -P release,ossrh
    else
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar
    fi
else
    mvn install
fi
