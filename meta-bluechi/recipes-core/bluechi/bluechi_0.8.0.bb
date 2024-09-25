SUMMARY = "recipe to install eclipse-bluechi"
DESCRIPTION = "install eclipe bluechi"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = "gitsm://github.com/eclipse-bluechi/bluechi.git;protocol=https;branch=main"
SRCREV = "1fc774161570db0d115543b96c549f7dd9deb20d"

S = "${WORKDIR}/git"

DEPENDS=" \
  sdbus-c++ \
  glib-2.0 \
  ninja \
"

EXTRA_OEMESON = "-Dwerror=false ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', '', '-Dwith_selinux=false', d)}"

FILES:${PN} += "${libdir}/* ${datadir}/* ${sysconfdir}/*"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = " bluechi-controller.service bluechi-agent.service"

SRC_URI += "file://bluechi-controller.conf"
SRC_URI += "file://bluechi-agent.conf"

inherit pkgconfig cmake meson systemd overlayfs

do_install:append() {
    if [ -d ${WORKDIR}/sources-unpack ]; then
        CFG_DIR=${WORKDIR}/sources-unpack
    else
        CFG_DIR=${WORKDIR}
    fi

    install -D ${CFG_DIR}/bluechi-controller.conf ${D}/etc/bluechi/controller.conf.d/00-default.conf
    install -D ${CFG_DIR}/bluechi-agent.conf ${D}/etc/bluechi/agent.conf.d/00-default.conf
}

REQUIRED_DISTRO_FEATURES += "systemd"
