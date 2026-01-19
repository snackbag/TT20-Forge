# script to copy build files into one public-builds directory
version=0.7.1

mkdir -p publish-builds

cp versions/1.19.2/build/libs/tt20-$version.jar publish-builds/tt20-$version+mc1.19.2.jar
cp versions/1.20.1/build/libs/tt20-$version.jar publish-builds/tt20-$version+mc1.20.1.jar
cp versions/1.20.6/build/libs/tt20-$version.jar publish-builds/tt20-$version+mc1.20.6.jar
cp versions/1.21/build/libs/tt20-$version.jar publish-builds/tt20-$version+mc1.21.jar
cp versions/1.21.5/build/libs/tt20-$version.jar publish-builds/tt20-$version+mc1.21.5.jar
cp versions/1.21.9/build/libs/tt20-$version.jar publish-builds/tt20-$version+mc1.21.9.jar
cp versions/1.21.10/build/libs/tt20-$version.jar publish-builds/tt20-$version+mc1.21.10.jar