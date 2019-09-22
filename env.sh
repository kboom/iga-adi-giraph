#!/usr/bin/env bash
THIS_DIR=$(dirname "$THIS")
BIN_DIR=$(cd "$THIS_DIR/bin" || exit ; pwd)

export PATH="${PATH}:${BIN_DIR}"
