if  [ "$TRAVIS_SECURE_ENV_VARS" = "true" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];then
    mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy sonar:sonar --settings ..\.settings.xml -P release,ossrh
else
    mvn install
fi
