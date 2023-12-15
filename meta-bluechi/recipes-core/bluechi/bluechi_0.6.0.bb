SUMMARY = "recipe to install eclipse-bluechi"
DESCRIPTION = "install eclipe bluechi"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = "gitsm://github.com/eclipse-bluechi/bluechi.git;protocol=https;branch=main"
SRCREV = "843317a2c2174acb439948d73fde30f9e8006585"

S = "${WORKDIR}/git"

DEPENDS=" \
  sdbus-c++ \
  glib-2.0 \
  ninja \
"

EXTRA_OEMESON = "-Dwerror=false"

FILES:${PN} += "${libdir}/* ${datadir}/*"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = " bluechi-controller.service bluechi-agent.service"

SRC_URI += " file://bluechi-agent.conf \
  file://bluechi-controller.conf \
  ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', '', 'file://selinux.patch', d)} \
"

inherit pkgconfig cmake meson systemd

do_install:append() {
    install -D ${WORKDIR}/bluechi-controller.conf ${D}/etc/bluechi/controller.conf.d/00-default.conf
    install -D ${WORKDIR}/bluechi-agent.conf ${D}/etc/bluechi/agent.conf.d/00-default.conf
}

REQUESTED_DISTRO_FEATURES = "systems"
