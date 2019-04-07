#!/usr/bin/env bash
if  [ "$TRAVIS_SECURE_ENV_VARS" = "true" ];then
    if [ "$TRAVIS_PULL_REQUEST" = "false" ];then
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy sonar:sonar --settings .settings.xml -P release,ossrh
    else
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar
    fi
else
    mvn install
fi
