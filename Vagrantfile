Vagrant.configure("2") do |config|

  config.vm.box = "archlinux/archlinux"
  
  config.vm.provider "virtualbox" do |vb|
	vb.customize ["modifyvm", :id, "--vram", "128"]
	vb.customize ["modifyvm", :id, "--graphicscontroller", "vmsvga"]
	vb.memory = 2048
  end
  
  config.vm.provision "file", source: "Backend", destination: "Backend"
  config.vm.provision "file", source: "Frontend", destination: "Frontend"
  config.vm.provision "file", source: "Database", destination: "Database"
  config.vm.provision :shell, :path => "provision.sh"

  
  config.vm.define "app" do |app|
	app.vm.network :private_network, :ip => "192.168.20.21"
  end
  
end
