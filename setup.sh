# Check if Java8 + is installed or not

which java
if [ $? -eq 0 ]; then
    echo "Java is installed"
else
    echo "Java is not installed"
    echo "Installing Java"
    sudo apt-get install openjdk-8-jdk
fi

# Install apache commons in /usr/local/lib
wget http://apache.mirrors.pair.com//commons/net/binaries/commons-net-3.6-bin.tar.gz
sudo mkdir /usr/local/lib/commons-net-3.6
sudo tar -xvzf commons-net-3.6-bin.tar.gz -C /usr/local/lib/commons-net-3.6 --strip-components 1
