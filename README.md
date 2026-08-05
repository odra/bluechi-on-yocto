# Bluechi on Yocto

This repository contains several Bitbake layers, recipes, etc to include Eclipse Bluechi
in an Yocto build.

## Project Structure

* `meta-bluechi`: A bitbake layer that builds and deploys bluechi;
* `bluechi-machine`: KAS build files to build a minimal working image with Eclipse Bluechi.

## Migration Notes

## Deprecation of `S = "${WORKDIR}/git"`

```
ERROR: bluechi-0.10.0-r0 do_unpack: Recipes that set S = "${WORKDIR}/git" or S = "${UNPACKDIR}/git" should remove that assignment, as S set by bitbake.conf in oe-core now works.
ERROR: Logfile of failure stored in: /workspace/bluechi-machine/build/tmp/work/x86-64-v3-oe-linux/bluechi/0.10.0/temp/log.do_unpack.204076
ERROR: Task (/workspace/bluechi-machine/build/../../meta-bluechi/recipes-core/bluechi/bluechi_0.10.0_git.bb:do_unpack) failed with exit code '1'
```

### Deprecation of systemd-compat-units

Using `VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"` is not valid anymore since systemd is dropping **SysVinit** compatibility support. See:

- https://docs.yoctoproject.org/6.0/migration-guides/migration-6.0.html#support-for-sysvinit-compatibility-in-systemd-was-dropped
- https://git.openembedded.org/openembedded-core/commit/?id=d9ec9e20eebc062d084dd76b59d665994e0cb51b

## License

[MIT](./LICENSE)
