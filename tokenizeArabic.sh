#!/bin/bash

# Call ArabicTokenizer 
# java -jar ./dist/ArabicTokenizer.jar <[-i|--input] [in-filename]> <[-o|--output] [out-filename]>
# or
# java -jar ./dist/ArabicTokenizer.jar < infile > outfile

java -jar ./dist/ArabicTokenizer.jar < $1 > $2

