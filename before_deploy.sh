branchName=$1
PR=$2
name=$3
email=$4

echo $branchName
echo $PR
echo $name
echo $email

if  [ $1 = "master" ] && [ $2 = "false" ]
then
	git config --local user.name $3
    git config --local user.email $4
    version=$(grep -Po -m1 '<version>(.*)</version>' pom.xml | grep -Po '(\d\.\d)')
    git tag $version
fi