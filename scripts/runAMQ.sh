#!/bin/bash
docker run -it -p 61616:61616 -p 8161:8161 -v $(pwd)/data:/var/lib/artemis-instance artemis-centos 
