inherit core-image

IMAGE_LINGUAS = "en-us"

IMAGE_NAME = "bluechi-minimal"

IMAGE_INSTALL += "\
    make \
    git \
    bzip2 \
    gcc \
    meson \
    ninja \
    bluechi \
"
