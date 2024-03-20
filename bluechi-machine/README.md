# BlueChi Machine

This folder contains the necessary KAS files to build a minimal BlueChi imagine.

It uses Poky Nanbield as the base distro.

## Building

```sh
$ kas build kas/qemux86_64.yml
```

## Running

```sh
$ kas shell kas/qemux86_64.yml
$ runqemu qemux86 nographic
```

You can login using "root" as username (no password).

## License

[MIT](./LICENSE)
