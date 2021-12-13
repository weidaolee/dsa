#!/usr/bin/env python

import os
import glob
import argparse


def parse():
    parser = argparse.ArgumentParser()
    parser.add_argument("--rename", type=str)
    parser.add_argument("--to", type=str)
    return parser.parse_args()

def rename_packege(args):
    file_list = glob.glob("./**/*.java")
    for s in file_list:
        with open(s, "r") as f:
            code = f.readlines()


        code[0] = code[0].replace(args.rename, args.to)
        with open(s, "w") as f:
            for line in code:
                f.write(line)


if __name__ == '__main__':
    args = parse()
    rename_packege(args)
