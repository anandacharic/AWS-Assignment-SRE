packer {
  required_plugins {
    amazon = {
      source  = "github.com/hashicorp/amazon"
      version = ">= 1.0.0"
    }
  }
}

variable "ami_users" {}
variable "aws_region" {}
variable "source_ami" {}
variable "ssh_username" {}
variable "ami-regions" {}
variable "aws_access_key" {
  type    = string
  default = null
}
variable "aws_secret_key" {
  type    = string
  default = null
}

source "amazon-ebs" "my-ami" {

  access_key      = "${var.aws_access_key}"
  secret_key      = "${var.aws_secret_key}"
  region          = "${var.aws_region}"
  ami_name        = "csye6225_${formatdate("YYYY_MM_DD_hh_mm_ss", timestamp())}"
  ami_description = "AMI for CSYE 6225"
  ami_users       = "${var.ami_users}"
  ami_regions     = "${var.ami-regions}"
  instance_type   = "t2.micro"
  source_ami      = "${var.source_ami}"
  ssh_username    = "${var.ssh_username}"
}

build {
  sources = ["source.amazon-ebs.my-ami"]

  provisioner "file" {
    source      = "./staging"
    destination = "/tmp"
  }

  provisioner "shell" {
    environment_vars = [
      "DEBIAN_FRONTEND=noninteractive",
      "CHECKPOINT_DISABLE=Y"
    ]
    inline = [
      "sudo apt update",
      "sudo apt install -y maven",
      # Added User
      "sudo groupadd usrgrp",
      "sudo useradd -s /bin/false -g usrgrp -d /opt/usr1 -m usr1",
      # Coping the files
      "sudo cp /tmp/staging/*.jar /opt/usr1/",
      "sudo cp /tmp/staging/appli.service /etc/systemd/system/",
      "sudo cp /tmp/staging/users.csv /opt",
      #Chnaging the permissionsfor users
      "sudo chown usr1:usrgrp /etc/systemd/system/appli.service",
      "sudo chown -R usr1:usrgrp /opt/usr1",
      "sudo chmod 664 /etc/systemd/system/appli.service",
      #Running the systemctl commands
      "sudo systemctl start appli.service",
      "sudo systemctl enable appli",

      # "sudo cp /tmp/staging/*.jar /opt",
      # "sudo cp /tmp/staging/users.csv /opt",
      # "sudo systemctl daemon-reload",

    ]
  }
}