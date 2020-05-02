#!/bin/bash
convert $1 -resize 512x512^ \
-define convolve:scale='-1!' \
          -morphology Convolve Laplacian:2 \
          -auto-level \
          -colorspace gray \
 out/${1%%.jpg}.png
#	$1 -resize 512x512^ +append \
