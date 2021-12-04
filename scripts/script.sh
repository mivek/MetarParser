#!/usr/bin/env bash
if  [[ "$TRAVIS_SECURE_ENV_VARS" = "true" ]];then
    # The merge will not be deployed but the build after the bump pom will be deployed to maven central
    if [[ "$TRAVIS_PULL_REQUEST" = "false" ]] && [[ "$TRAVIS_BRANCH" = "master" ]] && [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Bump pom) ]] ;then
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy sonar:sonar --settings .settings.xml -P release,ossrh
    else
        # If the branch is master then the deploy goal is used otherwise the maven install is used
        # Test against oracledk 8
        jdk_switcher use oraclejdk8
        mvn clean verify
        # Test against openjdk 8
        jdk_switcher use openjdk8
        mvn clean verify
        # Test against oracle jdk 11
        export JAVA_HOME=$HOME/oraclejdk11
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar
    fi
else
    # If the branch is master then the deploy goal is used otherwise the maven install is used
    # Test against oracledk 8
    jdk_switcher use oraclejdk8
    mvn clean verify
    # Test against openjdk 8
    jdk_switcher use openjdk8
    mvn clean verify
    # Test against oracle jdk 11
    export JAVA_HOME=$HOME/oraclejdk11
    mvn install
fi
