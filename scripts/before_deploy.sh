set -ev

if  [ $TRAVIS_BRANCH = "master" ] && [ $TRAVIS_PULL_REQUEST = "false" ]
then
	git config --local user.name $NAME
    git config --local user.email $EMAIL
    version=$(grep -Po -m1 '<version>(.*)</version>' pom.xml | grep -Po '(\d\.\d)')
    git tag $version
fi