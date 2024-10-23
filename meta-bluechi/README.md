# bluechi-on-yocto

This README file contains information on the contents of the meta-bluechi layer.

Please see the corresponding sections below for details.

Dependencies
============

  URI: git://git.yoctoproject.org/meta-virtualization
  branch: styhead

  URI: git://git.openembedded.org/openembedded-core
  branch: styhead

Patches
=======

Submit patches to its Gitlab repository: https://gitlab.com/autosd-on-yocto/meta-bluechi

Table of Contents
=================

  I. Adding the meta-bluechi layer to your build
 II. Building an Image
III. Selinux Support


I. Adding the meta-bluechi layer to your build
=================================================

Run 'bitbake-layers add-layer meta-bluechi'

II. Building an Image
=====================

This layer has a minimal image you can build by running: `bitbake bluechi-minimal`.

It needs the following configuration in your `local.conf` file:

```
DISTRO_FEATURES:append = " usrmerge systemd acl xattr pam"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"

VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"
```

The bluechi recipe will also include a basic single-node node configuration for testing purposes.

III. Selinux Support
====================

It's possible to build this layer with selinux support enabled but it has not being fully tested yet.
