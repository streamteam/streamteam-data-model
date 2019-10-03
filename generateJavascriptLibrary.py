#!/usr/bin/python

#
# StreamTeam
# Copyright (C) 2019  University of Basel
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as
# published by the Free Software Foundation, either version 3 of the
# License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
#
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <https://www.gnu.org/licenses/>.
#

import fnmatch
import os

# https://www.quora.com/Whats-the-easiest-way-to-recursively-get-a-list-of-all-the-files-in-a-directory-tree-in-Python
# https://docs.python.org/3/library/fnmatch.html#fnmatch.fnmatch
list = []
for root, directories, filenames in os.walk('src/main/protobuf/streamTeam/'):
    for filename in filenames:
        if fnmatch.fnmatch(filename, '*Payload.proto'):
            fullPathString = os.path.join(root, filename)
            shortPathString = fullPathString.replace("src/main/protobuf/streamTeam/", "")
            shortPathStringParts = shortPathString.split("/");

            if len(shortPathStringParts) == 2:
                prefix = "streamTeam." + shortPathStringParts[0] + "."
                protofile = shortPathStringParts[1]
            else:
                prefix = "streamTeam."
                protofile = shortPathStringParts[0]

            messageName = protofile.replace(".proto", "")
            # https://stackoverflow.com/questions/1549641/how-to-capitalize-the-first-letter-of-each-word-in-a-string-python#1549983
            messageName = ' '.join(word[0].upper() + word[1:] for word in messageName.split())

            tuple = ("protobuf/streamTeam/" + shortPathString, prefix + messageName, messageName);

            print tuple

            list.append(tuple)

# https://www.w3schools.com/python/python_file_write.asp
f = open("src/main/javascript/streamteam-data-model-lib.js", "w")

f.write("/*\n")
f.write(" * StreamTeam\n")
f.write(" * Copyright (C) 2019  University of Basel\n")
f.write(" *\n")
f.write(" * This program is free software: you can redistribute it and/or modify\n")
f.write(" * it under the terms of the GNU Affero General Public License as\n")
f.write(" * published by the Free Software Foundation, either version 3 of the\n")
f.write(" * License, or (at your option) any later version.\n")
f.write(" *\n")
f.write(" * This program is distributed in the hope that it will be useful,\n")
f.write(" * but WITHOUT ANY WARRANTY; without even the implied warranty of\n")
f.write(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n")
f.write(" * GNU Affero General Public License for more details.\n")
f.write(" *\n")
f.write(" * You should have received a copy of the GNU Affero General Public License\n")
f.write(" * along with this program.  If not, see <https://www.gnu.org/licenses/>.\n")
f.write(" */\n\n")

f.write("// This file has been generated automatically with generateJavascriptLibrary.py. DO NOT MODIFY IT!\n\n");

f.write("// https://stackoverflow.com/questions/2188218/relative-paths-in-javascript-in-an-external-file/4440632#4440632\n")
f.write("var jsFileLocation = $('script[src*=streamteam-data-model-lib]').attr('src');  // the js file path\n")
f.write("jsFileLocation = jsFileLocation.replace('streamteam-data-model-lib.js', '');   // the js folder path\n\n")

f.write("protobuf.load([jsFileLocation + \"/protobuf/streamTeam/immutableDataStreamElementContent.proto\"")
for tuple in list:
    f.write(",\n\tjsFileLocation + \"" + tuple[0] + "\"")
f.write("], function(err, root) {\n\n")

f.write("\tif (err)\n")
f.write("\t\tthrow err;\n\n")

f.write("\tImmutableDataStreamElementContent = root.lookupType(\"streamTeam.ImmutableDataStreamElementContent\");\n\n")

for tuple in list:
    f.write("\t" + tuple[2] + " = root.lookupType(\"" + tuple[1] + "\");\n")
f.write("});\n\n")

f.write("function decodeBase64EncodedImmutableDataStreamElement(input) {\n")
f.write("\t//https://github.com/protobufjs/protobuf.js/issues/735#issuecomment-292107345\n")
f.write("\tvar buffer = Uint8Array.from(window.atob(input), c => c.charCodeAt(0));\n")
f.write("\tvar immutableDataStreamElement = ImmutableDataStreamElementContent.decode(buffer);\n")

f.write("\t// https://github.com/protocolbuffers/protobuf/issues/2612#issuecomment-442700602\n")
f.write("\tvar payloadBuffer = Uint8Array.from(immutableDataStreamElement.payload.value);\n")
f.write("\tswitch (immutableDataStreamElement.payload.type_url) {\n")

for tuple in list:
    f.write("\t\tcase \"type.googleapis.com/" + tuple[1] + "\":\n")
    f.write("\t\t\timmutableDataStreamElement.payload = " + tuple[2] + ".decode(payloadBuffer);\n")
    f.write("\t\t\tbreak;\n")
f.write("\t\tdefault:\n")
f.write("\t\t\tconsole.warn(\"Don't know how to deal with this any type: \" + immutableDataStreamElement.payload.type_url);\n")
f.write("\t}\n\n")

f.write("\treturn immutableDataStreamElement;\n")
f.write("}")

f.close()
