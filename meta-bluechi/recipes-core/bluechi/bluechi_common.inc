SUMMARY = "recipe to install eclipse-bluechi"
DESCRIPTION = "install eclipe bluechi"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = " \
    sdbus-c++ \
    glib-2.0 \
    ninja \
"

SRC_URI:append = " file://bluechi-controller.conf file://bluechi-agent.conf file://bluechi-run-dir.conf"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = " bluechi-controller.service bluechi-agent.service"

inherit pkgconfig cmake meson systemd overlayfs

EXTRA_OEMESON = "-Dwerror=false ${@bb.utils.contains('DISTRO_FEATURES', 'selinux', '', '-Dwith_selinux=false', d)}"

do_install:append() {
    # copy bluechi config files (controller + local agent)
    install -D ${UNPACKDIR}/bluechi-controller.conf ${D}/etc/bluechi/controller.conf.d/00-default.conf
    install -D ${UNPACKDIR}/bluechi-agent.conf ${D}/etc/bluechi/agent.conf.d/00-default.conf

    # setup bluechi runtime directory via systemd-tmpfiles
    # fix QA error: runtimedir is expected to be empty [empty-dirs]
    rmdir ${D}/run/bluechi
    rmdir ${D}/run
    install -D ${UNPACKDIR}/bluechi-run-dir.conf  ${D}${libdir}/tmpfiles.d/bluechi-run-dir.conf
}

FILES:${PN} += "${libdir}/* ${datadir}/* ${sysconfdir}/*"
