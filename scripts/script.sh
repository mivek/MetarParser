if  [ "$TRAVIS_SECURE_ENV_VARS" = "true" ];then
    if [ "$TRAVIS_PULL_REQUEST" = "false" ]; then
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy sonar:sonar --settings ../.settings.xml -P release,ossrh
    else
        mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.branch.name=$TRAVIS_PULL_REQUEST_BRANCH
    fi
else
    mvn install
fi